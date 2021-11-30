package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeNotLoggedInController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/home")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "homeNotLoggedIn";
    }

    @RequestMapping(value = "/signup")
    public String signUp() {
        return "signUp";
    }

}