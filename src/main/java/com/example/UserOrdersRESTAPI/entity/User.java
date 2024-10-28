package com.example.UserOrdersRESTAPI.entity;

import com.example.UserOrdersRESTAPI.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserSummary.class)
    private Integer id;

    @NotNull(message = "Name cannot be null")
    @JsonView(Views.UserSummary.class)
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be correct")
    @JsonView(Views.UserSummary.class)
    private String email;

    @JsonView(Views.UserDetails.class)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrderList() {
        return orders;
    }

    public void setOrderList(List<Order> orderList) {
        this.orders = orderList;
    }
}
