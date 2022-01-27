package com.example.demo.bridgelabz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.bridgelabz.dto.OrderDto;
import com.example.demo.bridgelabz.model.OrderData;

@Service
public interface IOrderService {

    OrderData placeOrder(String token,int bookId ,  OrderDto orderDto);

    String cancelOrder(String token, int orderId);

    List<OrderData> userOrders(String token);

    List<OrderData> allOrders();
}