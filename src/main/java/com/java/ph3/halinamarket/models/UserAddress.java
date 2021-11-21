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
@Table(name = "user_address")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_address_id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "zip_code")
    private int zip_code;

    @OneToMany(mappedBy = "userByUserAddressId")
    private List<User> userList;
}
