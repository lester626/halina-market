package com.java.ph3.halinamarket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private float price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private Category productByCategoryId;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "subcategory_id", nullable = false)
    private SubCategory productBySubCategoryId;

    @OneToMany(mappedBy = "orderLinesByProductId")
    private List<OrderLines> orderLines;
}
