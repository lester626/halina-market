package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllCategories(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "category";
    }
}
