package com.example.demo.bridgelabz.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CartServiceDto {
    public  int userId;
    public  int bookId;
    public int quantity;
}