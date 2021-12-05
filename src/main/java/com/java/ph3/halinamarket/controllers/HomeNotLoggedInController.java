package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.repository.CategoryRepository;
import com.java.ph3.halinamarket.security_login.AuthenticationSystem;
import com.java.ph3.halinamarket.services.HomeNotLoggedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeNotLoggedInController {

    @Autowired
    HomeNotLoggedInService homeNotLoggedInService;

    @GetMapping("/home")
    public String home(ModelMap modelMap) {
        return homeNotLoggedInService.home(modelMap);
    }
}