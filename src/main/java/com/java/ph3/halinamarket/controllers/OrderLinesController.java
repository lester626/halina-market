package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.*;
import com.java.ph3.halinamarket.repository.*;
import com.java.ph3.halinamarket.services.OrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

@Controller
public class OrderLinesController {

    @Autowired
    OrderLinesService orderLinesService;

    @GetMapping("/order/lines")
    public String displayAllOrderLines(ModelMap modelMap, HttpServletRequest request) {
        return orderLinesService.findAllOrderLines(modelMap, request);
    }

    @PostMapping("/order/lines")
    public String checkoutOrders() {
        return orderLinesService.checkingOutOrders();
    }

    @GetMapping("/order/lines/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, ModelMap modelMap) {
        return orderLinesService.edittingProduct(id, modelMap);
    }

    @PostMapping("/order/lines/product/edit/{id}")
    public String edittedProduct(@ModelAttribute("order") OrderLines orderLines) {
        return orderLinesService.productEditted(orderLines);
    }

    @GetMapping("/order/lines/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        return orderLinesService.deletingProduct(id);
    }
}
