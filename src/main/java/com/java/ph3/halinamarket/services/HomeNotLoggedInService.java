package com.java.ph3.halinamarket.services;

import com.java.ph3.halinamarket.repository.CategoryRepository;
import com.java.ph3.halinamarket.security_login.AuthenticationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class HomeNotLoggedInService {

    @Autowired
    CategoryRepository categoryRepository;

    public String home(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        if (AuthenticationSystem.isLogged()) return "redirect:/home/loggedin";
        return "homeNotLoggedIn";
    }
}
