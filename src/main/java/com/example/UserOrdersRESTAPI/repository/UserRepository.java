package com.example.UserOrdersRESTAPI.repository;

import com.example.UserOrdersRESTAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
