package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.Category;
import com.java.ph3.halinamarket.repository.CategoryRepository;
import com.java.ph3.halinamarket.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubCategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @GetMapping("/category/sub/{id}")
    public String getSubCategoryById(@PathVariable("id") int id, ModelMap modelMap) {
        Category category = categoryRepository.getById(id);
        modelMap.addAttribute("category", category);
        modelMap.addAttribute("subcategories", subCategoryRepository.getSubCategoriesByCategoryByCategoryId_SubCategories(category));
        return "subcategory";
    }
}
