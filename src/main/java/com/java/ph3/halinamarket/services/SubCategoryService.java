package com.java.ph3.halinamarket.services;

import com.java.ph3.halinamarket.models.SubCategory;
import com.java.ph3.halinamarket.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public Page<SubCategory> listAll() {
        Pageable pageable = PageRequest.of(0, 10);
        return subCategoryRepository.findAll(pageable);
    }
}