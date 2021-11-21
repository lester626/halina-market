package com.java.ph3.halinamarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/category/sub")
public class ProductsController {

    @RequestMapping(value = "/prod", method = RequestMethod.GET)
    public String getAllProducts() {
        return "products";
    }
}
