package com.java.ph3.halinamarket.repository;

import com.java.ph3.halinamarket.models.Order;
import com.java.ph3.halinamarket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.orderByUserId = ?1")
    List<Order> findOrdersByOrderByUserId(User user);
}
