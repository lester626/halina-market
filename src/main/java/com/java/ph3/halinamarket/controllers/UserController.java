package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.DeliveryAddress;
import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.DeliveryAddressRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import com.java.ph3.halinamarket.security_login.AuthenticationSystem;
import com.java.ph3.halinamarket.security_login.CustomUserDetails;
import com.java.ph3.halinamarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return userService.login();
    }

    @GetMapping("/signup")
    public String signUpNow(ModelMap modelMap){
        return userService.signingUp(modelMap);
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("signup") User user){
        return userService.signsUp(user);
    }

    @GetMapping("/signup/delivery/address")
    public String viewDeliveryAddress(ModelMap modelMap) {
        return userService.signUpDeliveryAddress(modelMap);
    }

    @PostMapping("/signup/delivery/address")
    public String addDeliveryAddress(@ModelAttribute("deliveryAddress") DeliveryAddress deliveryAddress) {
        return userService.addingDeliverAddress(deliveryAddress);
    }

    @GetMapping("/signup/success")
    public String success(){
        return "signup-success";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/home";
    }

    @GetMapping("/myProfile")
    public String displayUserDetails(@AuthenticationPrincipal CustomUserDetails user, ModelMap modelMap, HttpServletRequest request) {
        return userService.toDisplayUserDetails(user, modelMap, request);
    }
}
