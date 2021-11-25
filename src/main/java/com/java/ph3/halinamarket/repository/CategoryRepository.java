package com.java.ph3.halinamarket.repository;

import com.java.ph3.halinamarket.models.Category;
import com.java.ph3.halinamarket.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT s FROM Category s WHERE s.category_id = ?1")
    List<Category> getCategoriesByCategory_id(Category categoryId);
}
