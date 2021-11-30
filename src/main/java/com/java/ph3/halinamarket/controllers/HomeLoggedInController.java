package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.Product;
import com.java.ph3.halinamarket.repository.CategoryRepository;
import com.java.ph3.halinamarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeLoggedInController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/home/loggedin")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("product", new Product());
        return "homeLoggedIn";
    }

    @PostMapping("/home/loggedin")
    public String resultProductByName(@ModelAttribute("product") final Product product, ModelMap modelMap) {
        modelMap.addAttribute("products", productRepository.searchByNameLike(product.getProductName()));
        return "products";
    }
}