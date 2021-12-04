package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.Order;
import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.OrderHolderRepository;
import com.java.ph3.halinamarket.repository.OrderRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class OrderController {

    @Autowired
    OrderHolderRepository orderHolderRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    private User currentLoggedInUser = new User();

    @GetMapping("/orders")
    public String checkoutOrders(ModelMap modelMap, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        currentLoggedInUser = userRepository.getUserByEmail(principal.getName());
        List<Order> allOrders = orderRepository.findOrdersByOrderByUserId(currentLoggedInUser);
        if(allOrders.isEmpty()) {
            allOrders = null;
        }
        modelMap.addAttribute("ordersCheckout", allOrders);
        return "orders";
    }

    @GetMapping("/orders/view")
    public String noOrders(ModelMap modelMap) {
        List<Order> orders = null;
        modelMap.addAttribute("ordersCheckout", orders);
        return "orders";
    }
}
