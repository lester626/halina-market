package com.java.ph3.halinamarket.repository;

import com.java.ph3.halinamarket.models.Category;
import com.java.ph3.halinamarket.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    @Query("SELECT s FROM SubCategory s WHERE s.categoryByCategoryId = ?1")
    List<SubCategory> getSubCategoriesByCategoryByCategoryId_SubCategories(Category categoryId);
}
