package com.java.ph3.halinamarket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_line_holder")
public class OrderHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_holder_id;

    @Column(name = "total_products")
    private int totalProducts;

    @Column(name = "product_price")
    private float productPrice;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product orderHolderByProductId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = true)
    private Order orderHolderByOrderId;
}
