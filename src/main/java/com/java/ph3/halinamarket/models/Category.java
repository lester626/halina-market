package com.java.ph3.halinamarket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy="categoryByCategoryId")
    private List<SubCategory> subCategories;

    @OneToMany(mappedBy="productByCategoryId")
    private List<Product> categoryByProductId;
}
