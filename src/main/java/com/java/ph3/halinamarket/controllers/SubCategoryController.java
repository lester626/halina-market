package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.Category;
import com.java.ph3.halinamarket.repository.CategoryRepository;
import com.java.ph3.halinamarket.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category/sub")
public class SubCategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getSubCategoryById(@PathVariable("id") int id, ModelMap modelMap) {
        Category category = categoryRepository.getById(id);
        modelMap.addAttribute("testcategory", category);
        modelMap.addAttribute("subcategories", subCategoryRepository.getSubCategoriesByCategoryByCategoryId_SubCategories(category));
        return "subcategory";
    }
}
