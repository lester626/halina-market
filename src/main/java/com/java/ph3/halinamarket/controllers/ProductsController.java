package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.OrderLines;
import com.java.ph3.halinamarket.models.Product;
import com.java.ph3.halinamarket.models.SubCategory;
import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
public class ProductsController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderLinesRepository orderLinesRepository;

    private Product productDetails;
    private List<OrderLines> orderLinesList;

    @GetMapping("/category/prod/{id}")
    public String getAllProducts(@PathVariable("id") int id, ModelMap modelMap, Model model) {
        SubCategory subCategory = new SubCategory();
        subCategory = subCategoryRepository.getById(id);

        modelMap.addAttribute("products", productRepository.findProductsByProductBySubCategoryId(subCategory));
        return "products";
    }

    @GetMapping("/product/details/{id}")
    public String displayAllOrderLines(@PathVariable("id") int id, Model model) {
        productDetails = productRepository.getById(id);
        model.addAttribute("product", productDetails);
        model.addAttribute("orderLines", new OrderLines());
        return "product-details";
    }

    @PostMapping("/product/details/{id}")
    public String orderProduct(@ModelAttribute("orderLines") OrderLines orderLines, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(orderLines.getTotalProducts() <= 0) {
            return "redirect:/product/details/" + productDetails.getProduct_id();
        }
        User user = userRepository.getUserByEmail(principal.getName());
        float totalPrice = productDetails.getPrice() * orderLines.getTotalProducts();
        orderLines.setProductPrice(totalPrice);
        orderLines.setOrderLinesByProductId(productDetails);
        orderLines.setOrderLinesByUserId(user);
        orderLinesRepository.save(orderLines);
        return "redirect:/order/lines";
    }

    @GetMapping("/search")
    public String getProducts(ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        return "search";
    }

    @PostMapping("/search")
    public String resultProductByName(@ModelAttribute("product") final Product product, ModelMap modelMap) {
        modelMap.addAttribute("products", productRepository.searchByNameLike(product.getProductName()));
        return "products";
    }
}
