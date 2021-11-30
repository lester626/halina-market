package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.OrderLines;
import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.OrderLinesRepository;
import com.java.ph3.halinamarket.repository.ProductRepository;
import com.java.ph3.halinamarket.repository.SubCategoryRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class OrderLinesController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    OrderLinesRepository orderLinesRepository;

    @GetMapping("/order/lines")
    public String displayAllOrderLines(ModelMap modelMap, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.getUserByEmail(principal.getName());
        List<OrderLines> orderLinesList = orderLinesRepository.findOrderLinesByOrderLinesByUserId(user);
        modelMap.addAttribute("orderLinesList", orderLinesList);
        return "order-lines";
    }
}