package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.Order;
import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.OrderHolderRepository;
import com.java.ph3.halinamarket.repository.OrderRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import com.java.ph3.halinamarket.services.OrderService;
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
    OrderService orderService;

    @GetMapping("/orders")
    public String checkoutOrders(ModelMap modelMap, HttpServletRequest request) {
        return orderService.viewAllCheckedOutOrders(modelMap, request);
    }

    @GetMapping("/orders/view")
    public String noOrders(ModelMap modelMap) {
        return orderService.ifNoOrders(modelMap);
    }
}
