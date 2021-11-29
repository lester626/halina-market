package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/home")
    public String home(Model model, ModelMap modelMap) {
        model.addAttribute("welcomeHome", "Your favorite neighborhood store");
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "homeNotLoggedIn";
    }

    @RequestMapping(value = "/signup")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/categories")
    public String categories(Model model) {

        return "categories";
    }
}