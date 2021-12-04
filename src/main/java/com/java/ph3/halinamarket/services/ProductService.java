package com.java.ph3.halinamarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.ph3.halinamarket.repository.ProductRepository;
import com.java.ph3.halinamarket.models.Product;
import com.java.ph3.halinamarket.models.pagination.Paged;
import com.java.ph3.halinamarket.models.pagination.Paging;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Paged<Product> getPage(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return new Paged<>(productPage, Paging.of(productPage.getTotalPages(), pageNumber, size));
    }
}