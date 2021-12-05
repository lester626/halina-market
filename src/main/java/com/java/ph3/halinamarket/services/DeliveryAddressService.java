package com.java.ph3.halinamarket.services;

import com.java.ph3.halinamarket.models.DeliveryAddress;
import com.java.ph3.halinamarket.repository.DeliveryAddressRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Service
public class DeliveryAddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    public String viewDeliveryAddress(ModelMap modelMap) {
        modelMap.addAttribute("deliveryAddress", new DeliveryAddress());
        return "add-delivery";
    }

    @Transactional
    public String addDeliveryAddress(DeliveryAddress deliveryAddress, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        deliveryAddress.setUserByUserId(userRepository.getUserByEmail(principal.getName()));
        deliveryAddressRepository.save(deliveryAddress);
        return "redirect:/home/loggedin";
    }
}
