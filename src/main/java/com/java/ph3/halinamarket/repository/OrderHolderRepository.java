package com.java.ph3.halinamarket.repository;

import com.java.ph3.halinamarket.models.OrderHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHolderRepository extends JpaRepository<OrderHolder, Integer> {
}
