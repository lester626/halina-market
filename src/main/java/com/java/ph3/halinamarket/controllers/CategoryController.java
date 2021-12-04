package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.Category;
import com.java.ph3.halinamarket.models.SubCategory;
import com.java.ph3.halinamarket.repository.CategoryRepository;
import com.java.ph3.halinamarket.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @GetMapping("/category")
    public String getAllCategories(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "category";
    }

    @GetMapping("/category/add")
    public String viewToAdd(ModelMap modelMap) {
        List<Category> allCategories = categoryRepository.findAll();
        if(allCategories.isEmpty()) {
            allCategories = null;
        }
        modelMap.addAttribute("viewCategories", allCategories);
        modelMap.addAttribute("addCategory", new Category());
        return "add-category";
    }

    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute("addCategory") Category category) {
        SubCategory defaultSubCategory = new SubCategory();
        categoryRepository.save(category);
        defaultSubCategory.setName("Other " + category.getName());
        defaultSubCategory.setCategoryByCategoryId(categoryRepository.getCategoryByName(category.getName()));
        subCategoryRepository.save(defaultSubCategory);
        return "category-added";
    }
}
