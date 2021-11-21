package com.java.ph3.halinamarket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "accept_email")
    private boolean acceptEmail;

    @Column(name = "user_role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "user_address_id", referencedColumnName = "user_address_id", nullable = false)
    private UserAddress userByUserAddressId;

    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", acceptEmail=" + acceptEmail +
                ", userByUserAddressId=" + userByUserAddressId +
                '}';
    }
}
