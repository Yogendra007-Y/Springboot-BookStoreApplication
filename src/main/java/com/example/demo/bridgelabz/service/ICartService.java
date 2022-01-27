package com.example.demo.bridgelabz.service;

import java.util.List;

import com.example.demo.bridgelabz.dto.CartServiceDto;
import com.example.demo.bridgelabz.model.CartData;

public interface ICartService {

    CartData addToCart(String token,CartServiceDto cartDTO);

    void deleteFromCart(int cartId);

    CartData updateQuantity(String token, int cartId, int quantity);

    List<CartData> findAllInCart(String token);
}