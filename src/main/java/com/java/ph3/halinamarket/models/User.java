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
    private int user_id;

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
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", acceptEmail=" + acceptEmail +
                ", userByUserAddressId=" + userByUserAddressId +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAcceptEmail(boolean b) {
        return acceptEmail;
    }

    public void setAcceptEmail(boolean acceptEmail) {
        this.acceptEmail = acceptEmail;
    }

    public String getRole(User user) {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserAddress getUserByUserAddressId(int i) {
        return userByUserAddressId;
    }

    public void setUserByUserAddressId(UserAddress userByUserAddressId) {
        this.userByUserAddressId = userByUserAddressId;
    }
}
