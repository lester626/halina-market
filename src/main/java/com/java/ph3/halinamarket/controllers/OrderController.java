package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.OrderHolderRepository;
import com.java.ph3.halinamarket.repository.OrderRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        modelMap.addAttribute("ordersCheckout", orderRepository.findOrdersByOrderByUserId(currentLoggedInUser));
        return "orders";
    }
}
