package com.java.ph3.halinamarket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @NotNull
    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    @Length(min = 8)
    @Column(name = "user_password")
    private String password;

    @NotNull
    @Column(name = "user_first_name")
    private String firstName;

    @NotNull
    @Column(name = "user_last_name")
    private String lastName;

    @AssertTrue
    @Column(name = "accept_email")
    private boolean acceptEmail;

    @NotNull
    @Column(name = "user_role")
    private String role;

    @NotNull
    @Column(name = "country")
    private String country;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "zip_code")
    private int zip_code;
}
