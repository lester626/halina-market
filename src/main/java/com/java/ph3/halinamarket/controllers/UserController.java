package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.DeliveryAddress;
import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.DeliveryAddressRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import com.java.ph3.halinamarket.security_login.CustomUserDetails;
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
    UserRepository userRepository;

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    private User userDetails;

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "homeLoggedIn";
    }

    @GetMapping("/signup")
    public String signUpNow(ModelMap modelMap){
        modelMap.addAttribute("signUp", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("signup") User user){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encrypted = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encrypted);
        user.setRole("ROLE_USER");
        userDetails = user;
        return "redirect:/signup/delivery/address";
    }

    @GetMapping("/signup/delivery/address")
    public String viewDeliveryAddress(ModelMap modelMap) {
        modelMap.addAttribute("deliveryAddress", new DeliveryAddress());
        return "delivery-address";
    }

    @PostMapping("/signup/delivery/address")
    public String addDeliveryAddress(@ModelAttribute("deliveryAddress") DeliveryAddress deliveryAddress) {
        userRepository.save(userDetails);
        deliveryAddress.setUserByUserId(userRepository.getUserByEmail(userDetails.getEmail()));
        deliveryAddressRepository.save(deliveryAddress);
        return "redirect:/signup/success";
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
        modelMap.addAttribute("user", user);
        Principal principal = request.getUserPrincipal();
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "myAdminProfile";
        }
        return "myProfile";
    }
}
