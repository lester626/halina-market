package com.java.ph3.halinamarket.repository;

import com.java.ph3.halinamarket.models.Product;
import com.java.ph3.halinamarket.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.productBySubCategoryId = ?1")
    List<Product> findProductsByProductBySubCategoryId(SubCategory subCat);

    @Query("SELECT p FROM Product p WHERE p.product_id = ?1")
    Product findProductByProduct_id(int id);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:name%")
    List<Product> searchByNameLike(@Param("name") String name);
}

