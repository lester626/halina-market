package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.User;
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

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

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
        userRepository.save(user);
        return "redirect:/signup/success";
    }

    @GetMapping("/signup/success")
    public String success(){
        return "signup-success";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("/myProfile")
    public String displayUserDetails(@AuthenticationPrincipal CustomUserDetails user, ModelMap modelMap) {
        modelMap.addAttribute("user", user);
        return "myProfile";
    }
}
