package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.Category;
import com.java.ph3.halinamarket.models.SubCategory;
import com.java.ph3.halinamarket.repository.CategoryRepository;
import com.java.ph3.halinamarket.repository.SubCategoryRepository;
import com.java.ph3.halinamarket.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/add")
    public String viewToAdd(ModelMap modelMap) {
        return categoryService.viewAllAdding(modelMap);
    }

    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute("addCategory") Category category) {
        return categoryService.addingCategory(category);
    }
}
