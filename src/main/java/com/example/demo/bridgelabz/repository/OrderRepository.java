package com.example.demo.bridgelabz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bridgelabz.model.OrderData;

public interface OrderRepository extends JpaRepository<OrderData, Integer> {

//    List<OrderData> findAllByUserId(int id);
}
