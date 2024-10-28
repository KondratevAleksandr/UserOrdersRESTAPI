package com.example.UserOrdersRESTAPI.entity;

import com.example.UserOrdersRESTAPI.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserSummary.class)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonView(Views.UserDetails.class)
    private User user;

    @Column(name = "product_details")
    @JsonView({Views.UserSummary.class})
    private String productDetails;

    @Column(name = "total_amount")
    @JsonView(Views.UserSummary.class)
    private double totalAmount;

    @Column(name = "order_status")
    @JsonView(Views.UserDetails.class)
    private String status;

    public Order() {
    }

    public Order(User user, String productDetails, double totalAmount, String status) {
        this.user = user;
        this.productDetails = productDetails;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
