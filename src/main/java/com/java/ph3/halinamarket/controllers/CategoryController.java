package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/halina")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getAllCategories(ModelMap modelMap) {
        String[] categories = new String[] {"Beverages", "Bread/Bakery", "Canned/Jarred Goods", "Dairy"};
        modelMap.addAttribute("categories", categories);
        return "category";
    }
}
