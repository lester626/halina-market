package com.java.ph3.halinamarket.repository;

import com.java.ph3.halinamarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaginationRepository extends JpaRepository<Product, Integer> {
}
