package com.java.ph3.halinamarket.repository;

import com.java.ph3.halinamarket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
