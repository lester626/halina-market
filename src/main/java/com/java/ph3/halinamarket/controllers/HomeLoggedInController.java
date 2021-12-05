package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.Product;
import com.java.ph3.halinamarket.repository.CategoryRepository;
import com.java.ph3.halinamarket.repository.ProductRepository;
import com.java.ph3.halinamarket.security_login.AuthenticationSystem;
import com.java.ph3.halinamarket.services.HomeLoggedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class HomeLoggedInController {

    @Autowired
    HomeLoggedInService homeLoggedInService;

    @GetMapping("/home/loggedin")
    public String home(ModelMap modelMap) {
        return homeLoggedInService.home(modelMap);
    }

    @PostMapping("/home/loggedin")
    public String resultProductByName(@ModelAttribute("product") final Product product, ModelMap modelMap) {
        return homeLoggedInService.homeWithProducts(product, modelMap);
    }
}