package com.java.ph3.halinamarket.services;

import com.java.ph3.halinamarket.models.Category;
import com.java.ph3.halinamarket.models.SubCategory;
import com.java.ph3.halinamarket.repository.CategoryRepository;
import com.java.ph3.halinamarket.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    private SubCategory defaultSubCategory = new SubCategory();

    public String viewAllAdding(ModelMap modelMap) {
        List<Category> allCategories = categoryRepository.findAll();
        if(allCategories.isEmpty()) {
            allCategories = null;
        }
        modelMap.addAttribute("viewCategories", allCategories);
        modelMap.addAttribute("addCategory", new Category());
        return "add-category";
    }

    @Transactional
    public String addingCategory(Category category) {
        categoryRepository.save(category);
        defaultSubCategory.setName("Other " + category.getName());
        defaultSubCategory.setCategoryByCategoryId(categoryRepository.getCategoryByName(category.getName()));
        subCategoryRepository.save(defaultSubCategory);
        return "add-category-success";
    }
}
