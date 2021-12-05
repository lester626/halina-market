package com.java.ph3.halinamarket.services;

import com.java.ph3.halinamarket.models.Order;
import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.OrderHolderRepository;
import com.java.ph3.halinamarket.repository.OrderRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderHolderRepository orderHolderRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    private User currentLoggedInUser = new User();

    public String viewAllCheckedOutOrders(ModelMap modelMap, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        currentLoggedInUser = userRepository.getUserByEmail(principal.getName());
        List<Order> allOrders = orderRepository.findOrdersByOrderByUserId(currentLoggedInUser);
        if(allOrders.isEmpty()) {
            allOrders = null;
        }
        modelMap.addAttribute("ordersCheckout", allOrders);
        return "orders";
    }

    public String ifNoOrders(ModelMap modelMap) {
        List<Order> orders = null;
        modelMap.addAttribute("ordersCheckout", orders);
        return "orders";
    }
}
