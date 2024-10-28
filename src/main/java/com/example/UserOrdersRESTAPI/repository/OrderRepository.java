package com.example.UserOrdersRESTAPI.repository;

import com.example.UserOrdersRESTAPI.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> getOrdersByUserId(int userId);
}
