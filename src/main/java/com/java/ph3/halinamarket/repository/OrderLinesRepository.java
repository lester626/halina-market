package com.java.ph3.halinamarket.repository;

import com.java.ph3.halinamarket.models.OrderLines;
import com.java.ph3.halinamarket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface OrderLinesRepository extends JpaRepository<OrderLines, Integer> {
    @Query("SELECT o FROM OrderLines o WHERE o.orderLinesByUserId = ?1")
    List<OrderLines> findOrderLinesByOrderLinesByUserId(User user);
}
