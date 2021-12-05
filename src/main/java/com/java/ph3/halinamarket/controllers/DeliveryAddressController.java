package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.DeliveryAddress;
import com.java.ph3.halinamarket.repository.DeliveryAddressRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import com.java.ph3.halinamarket.services.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class DeliveryAddressController {
    @Autowired
    DeliveryAddressService deliveryAddressService;

    @GetMapping("/add/delivery")
    public String directoryToAddDeliveryAddress() {
        return "prototype-add-delivery";
    }

    @GetMapping("/add/delivery/address")
    public String viewDeliveryAddress(ModelMap modelMap) {
        return deliveryAddressService.viewDeliveryAddress(modelMap);
    }

    @PostMapping("/add/delivery/address")
    public String addDeliveryAddress(@ModelAttribute("deliveryAddress") DeliveryAddress deliveryAddress, HttpServletRequest request) {
        return deliveryAddressService.addDeliveryAddress(deliveryAddress, request);
    }
}
