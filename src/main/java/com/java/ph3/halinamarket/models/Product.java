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
    private int product_id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "price")
    private float price;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "subcategory_id", nullable = false)
    private SubCategory productBySubCategoryId;

    @OneToMany(mappedBy = "orderLinesByProductId")
    private List<OrderLines> orderLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getProduct_id() == product.getProduct_id() && Float.compare(product.getPrice(), getPrice()) == 0 && getProductName().equals(product.getProductName()) && getProductDesc().equals(product.getProductDesc()) && getImageUrl().equals(product.getImageUrl()) && getProductBySubCategoryId().equals(product.getProductBySubCategoryId()) && getOrderLines().equals(product.getOrderLines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct_id(), getProductName(), getProductDesc(), getImageUrl(), getPrice(), getProductBySubCategoryId(), getOrderLines());
    }
}
