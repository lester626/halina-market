package com.java.ph3.halinamarket.controllers;

import com.java.ph3.halinamarket.models.*;
import com.java.ph3.halinamarket.repository.*;
import com.java.ph3.halinamarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    ProductService productService;

    @GetMapping("/category/prod/{id}")
    public String getAllProductsById(@PathVariable("id") int id, ModelMap modelMap, Pageable pageable) {
        return productService.findAllProducts(id, modelMap, pageable);
    }

    @GetMapping("/product/details/{id}")
    public String displayProductDetailsById(@PathVariable("id") int id, Model model) {
        return productService.getProductDetailsById(id, model);
    }

    @PostMapping("/product/details/{id}")
    public String orderProduct(@ModelAttribute("orderLines") OrderLines orderLines, HttpServletRequest request) {
        return productService.addProductToOrderLines(orderLines, request);
    }

    @GetMapping("/search")
    public String getProducts(ModelMap modelMap) {
        return productService.getProductsForSearch(modelMap);
    }

    @PostMapping("/search")
    public String resultProductByName(@ModelAttribute("product-search") final Product product, ModelMap modelMap) {
        return productService.resultForProductSearch(product, modelMap);
    }

    @GetMapping("/product/add")
    public String addingProduct(ModelMap modelMap) {
        return productService.toAddProduct(modelMap);
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("addProduct") Product addedProduct, HttpServletRequest request, ModelMap modelMap) {
        return productService.addsProduct(addedProduct, request, modelMap);
    }

    @GetMapping("/product/display/all")
    public String displayAllProductsForEdittingAndDelete(ModelMap modelMap) {
        return productService.displayingAllProducts(modelMap);
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, ModelMap modelMap) {
        return productService.edittingProduct(id, modelMap);
    }

    @PostMapping("/product/edit/{id}")
    public String productEditted(@ModelAttribute("productToEdit") Product product) {
        return productService.productEditSuccessfully(product);
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        return productService.productDelete(id);
    }
}
