package com.java.ph3.halinamarket.services;

import com.java.ph3.halinamarket.models.Product;
import com.java.ph3.halinamarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAll() {
        return productRepository.findAll();
    }
}