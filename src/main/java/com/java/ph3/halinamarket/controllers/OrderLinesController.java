package com.java.ph3.halinamarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/order")
public class OrderLinesController {

    @RequestMapping(value = "/lines", method = RequestMethod.GET)
    public String displayAllOrderLines() {
        return "order-lines";
    }
}
