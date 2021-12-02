package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.*;
import com.java.ph3.halinamarket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

@Controller
public class OrderLinesController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    OrderLinesRepository orderLinesRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderHolderRepository orderHolderRepository;

    private Order order = new Order();
    private OrderLines orderLinesDetails = new OrderLines();
    private List<OrderLines> orderLinesList = new ArrayList<>();
    private User currentUser = new User();
    private DeliveryAddress checkoutAddress = new DeliveryAddress();
    private OrderHolder orderHolder = new OrderHolder();
    private SecureRandom secureRandom = new SecureRandom();
    private List<OrderHolder> holderList = new ArrayList<>();

    @GetMapping("/order/lines")
    public String displayAllOrderLines(ModelMap modelMap, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        float total = 0;

        currentUser = userRepository.getUserByEmail(principal.getName());
        checkoutAddress = currentUser.getUserByDeliveryAddressId().get(0);
        orderLinesList = orderLinesRepository.findOrderLinesByOrderLinesByUserId(currentUser);
        for(OrderLines orderLine: orderLinesList) {
            total += (orderLine.getOrderLinesByProductId().getPrice() * orderLine.getTotalProducts());
        }
        modelMap.addAttribute("orderTotal", total);
        modelMap.addAttribute("orderLinesList", orderLinesList);
        return "order-lines";
    }

    @PostMapping("/order/lines")
    public String checkoutOrders() {
        LocalDate localDate = LocalDate.now();

        int randId = Math.abs(secureRandom.nextInt());

        float totalCost = 1;

        for(OrderLines orderLineCompute : orderLinesList) {
            totalCost += orderLineCompute.getProductPrice();
        }

        order.setOrder_id(randId);
        order.setTotalCost(totalCost);
        order.setOrderDate(localDate);
        order.setOrderStatus("Shipping");
        order.setOrderByDeliveryAddressId(checkoutAddress);
        order.setOrderByUserId(currentUser);
        orderRepository.save(order);

        for(OrderLines orderLine : orderLinesList) {
            int randHolderId = Math.abs(secureRandom.nextInt());
            orderHolder.setOrder_holder_id(randHolderId);
            orderHolder.setProductPrice(orderLine.getProductPrice());
            orderHolder.setTotalProducts(orderLine.getTotalProducts());
            orderHolder.setOrderHolderByProductId(orderLine.getOrderLinesByProductId());
            orderHolder.setOrderHolderByOrderId(order);
            orderHolderRepository.save(orderHolder);
            orderLinesRepository.delete(orderLine);
        }
        return "redirect:/orders";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, ModelMap modelMap) {
        orderLinesDetails = orderLinesRepository.getById(id);
        System.out.println(orderLinesDetails.getOrderLinesByProductId().getProductName());
        System.out.println(orderLinesDetails.getTotalProducts());
        modelMap.addAttribute("order", orderLinesDetails);
        return "edit";
    }

    @PostMapping("/product/edit/{id}")
    public String edittedProduct(@ModelAttribute("order") OrderLines orderLines) {
        float totalPrice = 0;
        if(orderLines.getTotalProducts() <= 0) {
            orderLinesRepository.delete(orderLinesDetails);
        } else {
            orderLinesDetails.setTotalProducts(orderLines.getTotalProducts());
            totalPrice = orderLinesDetails.getOrderLinesByProductId().getPrice() * orderLinesDetails.getTotalProducts();
            orderLinesDetails.setProductPrice(totalPrice);
            orderLinesRepository.save(orderLinesDetails);
        }
        return "redirect:/order/lines";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        orderLinesRepository.delete(orderLinesRepository.getById(id));
        return "redirect:/order/lines";
    }
}
