package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.OrderLines;
import com.java.ph3.halinamarket.models.Product;
import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.OrderLinesRepository;
import com.java.ph3.halinamarket.repository.ProductRepository;
import com.java.ph3.halinamarket.repository.SubCategoryRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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

    private OrderLines orderLinesDetails;

    @GetMapping("/order/lines")
    public String displayAllOrderLines(ModelMap modelMap, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        float total = 0;
        User user = userRepository.getUserByEmail(principal.getName());
        List<OrderLines> orderLinesList = orderLinesRepository.findOrderLinesByOrderLinesByUserId(user);
        for(OrderLines orderLine: orderLinesList) {
            total += (orderLine.getOrderLinesByProductId().getPrice() * orderLine.getTotalProducts());
        }
        modelMap.addAttribute("orderTotal", total);
        modelMap.addAttribute("orderLinesList", orderLinesList);
        return "order-lines";
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
