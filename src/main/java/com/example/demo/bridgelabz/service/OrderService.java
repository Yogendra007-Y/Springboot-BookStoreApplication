package com.example.demo.bridgelabz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bridgelabz.dto.OrderDto;
import com.example.demo.bridgelabz.exception.UserRegistrationException;
import com.example.demo.bridgelabz.model.BookDetails;
import com.example.demo.bridgelabz.model.OrderData;
import com.example.demo.bridgelabz.model.UserRegistrationData;
import com.example.demo.bridgelabz.repository.BookDetailsRepository;
import com.example.demo.bridgelabz.repository.OrderRepository;
import com.example.demo.bridgelabz.repository.UserRegistrationRepository;
import com.example.demo.bridgelabz.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService implements  IOrderService{

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    UserRegistrationRepository userRepo;

    @Autowired
    OrderRepository orderRepo;
    
    @Autowired
	BookDetailsRepository bookRepo;


    @Override
	public OrderData placeOrder(String token, int bookId, OrderDto orderDto) {
		OrderData order = new OrderData();
		int id = Math.toIntExact(tokenUtil.decodeToken(token));
		Optional<UserRegistrationData> isUserPresent = userRepo.findById(id);
		Optional<BookDetails> isBookPresent = bookRepo.findById(bookId);
		if (isUserPresent.isPresent() && isBookPresent.isPresent()) {
			order.setUserRegistrationData(isUserPresent.get());
			order.setBookDetails(isBookPresent.get());
			order.crateOrder(orderDto);		
		}else {
			throw new UserRegistrationException("userid or bookid is invalid");
		}
		return orderRepo.save(order);
	}

    @Override
    public String cancelOrder(String token, int orderId) {
        int id = Math.toIntExact(tokenUtil.decodeToken(token));
        java.util.Optional<UserRegistrationData> isPresent = userRepo.findById(id);
        if (isPresent.isPresent()) {
            java.util.Optional<OrderData> order = orderRepo.findById(orderId);
            if (order.isPresent()) {
                order.get().setCancel(true);
                orderRepo.save(order.get());
                return "Cancel order Successfull";
            }

        }
        return  "cancel order not successfull";
    }

    @Override
    public List<OrderData> userOrders(String token) {
//        int id = Math.toIntExact(tokenUtil.decodeToken(token));
//        java.util.Optional<UserRegistrationData> isPresent = userRepo.findById(id);
//        if (isPresent.isPresent()) {
//            List<OrderData> orders = orderRepo.findAllByUserId(id);
//            return orders;
//        }
        return  null;
    }

    @Override
    public List<OrderData> allOrders() {
       List<OrderData> orders = orderRepo.findAll();
       if(orders.isEmpty()){
           return null;
       }else
       {
        for(int i=0;i< orders.size();i++) {
            int id = orders.get(i).getId();
            java.util.Optional<OrderData> orderByOrderId = orderRepo.findById(id);
            if (orderByOrderId.isPresent()) {
                orderByOrderId.get().setCancel(false);
                orderRepo.save(orderByOrderId.get());
                return orders;
            }
        }

       }return  null;
    }
}