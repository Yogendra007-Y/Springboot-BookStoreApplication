package com.example.demo.bridgelabz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.bridgelabz.model.CartData;

public interface CartRepository extends JpaRepository<CartData, Integer> {
    @Query(value = "SELECT * FROM cart where user_Id = :id", nativeQuery = true)
    List<CartData> findAllCartsByUserId(int id);
}