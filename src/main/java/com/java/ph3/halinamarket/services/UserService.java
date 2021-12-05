package com.java.ph3.halinamarket.services;

import com.java.ph3.halinamarket.models.DeliveryAddress;
import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.DeliveryAddressRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import com.java.ph3.halinamarket.security_login.AuthenticationSystem;
import com.java.ph3.halinamarket.security_login.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    private User userDetails;

    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (AuthenticationSystem.isLogged()) return "redirect:/home/loggedin";
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "homeLoggedIn";
    }

    public String signingUp(ModelMap modelMap) {
        modelMap.addAttribute("signUp", new User());
        return "signup";
    }

    @Transactional
    public String signsUp(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encrypted = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encrypted);
        user.setRole("ROLE_USER");
        userDetails = user;
        return "redirect:/signup/delivery/address";
    }

    public String signUpDeliveryAddress(ModelMap modelMap) {
        modelMap.addAttribute("deliveryAddress", new DeliveryAddress());
        return "delivery-address";
    }

    @Transactional
    public String addingDeliverAddress(DeliveryAddress deliveryAddress) {
        userRepository.save(userDetails);
        deliveryAddress.setUserByUserId(userRepository.getUserByEmail(userDetails.getEmail()));
        deliveryAddressRepository.save(deliveryAddress);
        return "redirect:/signup/success";
    }

    public String toDisplayUserDetails(CustomUserDetails user, ModelMap modelMap, HttpServletRequest request) {modelMap.addAttribute("user", user);
        Principal principal = request.getUserPrincipal();
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "myAdminProfile";
        }
        return "myProfile";
    }
}
