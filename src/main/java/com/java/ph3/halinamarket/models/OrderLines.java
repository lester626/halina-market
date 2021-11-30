package com.java.ph3.halinamarket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_lines")
public class OrderLines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_line_id;

    @Column(name = "total_products")
    private int totalProducts;

    @Column(name = "product_price")
    private float productPrice;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product orderLinesByProductId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User orderLinesByUserId;
}