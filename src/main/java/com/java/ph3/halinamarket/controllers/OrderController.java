package com.java.ph3.halinamarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @PostMapping("/checkout/products/{id}")
    public String displayAllCheckouts() {
        return "orders";
    }
}
