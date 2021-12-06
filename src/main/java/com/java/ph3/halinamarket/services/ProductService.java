package com.java.ph3.halinamarket.services;

import com.java.ph3.halinamarket.models.*;
import com.java.ph3.halinamarket.repository.OrderLinesRepository;
import com.java.ph3.halinamarket.repository.SubCategoryRepository;
import com.java.ph3.halinamarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.ph3.halinamarket.repository.ProductRepository;
import com.java.ph3.halinamarket.models.pagination.Paged;
import com.java.ph3.halinamarket.models.pagination.Paging;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderLinesRepository orderLinesRepository;

//    @Autowired
//    public List<Product> listAll() {
//        return productRepository.findAll();
//    }

    private Product productDetails;
    private List<OrderLines> orderLinesList;
    private Category categoryProductSuccess = new Category();
    private SubCategory subCategoryProductSuccess = new SubCategory();
    private Product productAddSuccess = new Product();
    private Product storeProductForEdit = new Product();

    public Paged<Product> getPage(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return new Paged<>(productPage, Paging.of(productPage.getTotalPages(), pageNumber, size));
    }

    public String findAllProducts(int id, ModelMap modelMap, Pageable pageable) {
        SubCategory subCategory = new SubCategory();
        subCategory = subCategoryRepository.getById(id);
        List<Product> products = productRepository.findProductsByProductBySubCategoryId(subCategory);
        if(products.isEmpty()) {
            products = null;
        }
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("productSearch", productRepository.findAll(pageable));
        return "products";
    }

    public String getProductDetailsById(int id, Model model) {
        productDetails = productRepository.getById(id);
        model.addAttribute("product", productDetails);
        model.addAttribute("orderLines", new OrderLines());
        return "product-details";
    }

    @Transactional
    public String addProductToOrderLines(OrderLines orderLines, HttpServletRequest request) {
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

    public String getProductsForSearch(ModelMap modelMap) {
        modelMap.addAttribute("product-search", new Product());
        return "search";
    }

    @Transactional
    public String resultForProductSearch(Product product, ModelMap modelMap) {
        modelMap.addAttribute("products", productRepository.searchByNameLike(product.getProductName()));
        return "products";
    }

    public String toAddProduct(ModelMap modelMap) {
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        modelMap.addAttribute("subCategoriesList", subCategoryList);
        modelMap.addAttribute("addProduct", new Product());
        return "addProducts";
    }

    @Transactional
    public String addsProduct(Product addedProduct, HttpServletRequest request, ModelMap modelMap) {
        SubCategory subCategory = subCategoryRepository.getSubCategoryByName(request.getParameter("sub-categories"));
        addedProduct.setProductBySubCategoryId(subCategory);
        addedProduct.setAvailability(true);
        productRepository.save(addedProduct);

        productAddSuccess = productRepository.getProductByProductName(addedProduct.getProductName());
        categoryProductSuccess = productAddSuccess.getProductBySubCategoryId().getCategoryByCategoryId();
        subCategoryProductSuccess = productAddSuccess.getProductBySubCategoryId();
        modelMap.addAttribute("categoryProduct", categoryProductSuccess);
        modelMap.addAttribute("subCategoryProduct", subCategoryProductSuccess);
        modelMap.addAttribute("productAdded", productAddSuccess);
        return "addProducts-success";
    }

    public String displayingAllProducts(ModelMap modelMap) {
//        modelMap.addAttribute("allProducts", productRepository.findProductsOrderByName());
        return "myAdminProfile";
    }

    public String edittingProduct(int id, ModelMap modelMap) {
        storeProductForEdit = productRepository.getById(id);
        modelMap.addAttribute("productToEdit", storeProductForEdit);
        return "products-admin-edit";
    }

    @Transactional
    public String productEditSuccessfully(Product product) {
        storeProductForEdit.setProductName(product.getProductName());
        storeProductForEdit.setProductDesc(product.getProductDesc());
        storeProductForEdit.setImageUrl(product.getImageUrl());
        storeProductForEdit.setPrice(product.getPrice());
        productRepository.save(storeProductForEdit);
        return "redirect:/myProfile";
    }

    @Transactional
    public String productDelete(int id) {
        Product productToDelete = productRepository.getById(id);
        productRepository.delete(productToDelete);
        return "redirect:/myProfile";
    }
}