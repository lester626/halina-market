package com.java.ph3.halinamarket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private int order_id;

    @Column(name = "total_cost")
    private float totalCost;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_status")
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User orderByUserId;

    @ManyToOne
    @JoinColumn(name = "del_address_id", referencedColumnName = "del_address_id")
    private DeliveryAddress orderByDeliveryAddressId;

    @OneToMany(mappedBy = "orderHolderByOrderId")
    List<OrderHolder> orderHolders;
}
