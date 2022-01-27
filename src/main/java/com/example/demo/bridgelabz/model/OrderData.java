package com.example.demo.bridgelabz.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.bridgelabz.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class OrderData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate orderDate;
	private int price;
	private int quantity;
	private String address;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_fk_id", referencedColumnName = "userId")
	private UserRegistrationData userRegistrationData;
	//private int userId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_fk_id", referencedColumnName = "bookId")
	private BookDetails bookDetails;
	//private int bookId;
	private boolean cancel = false;
    public void crateOrder(OrderDto orderDto) {
        this.orderDate = LocalDate.now();
        this.price = orderDto.getPrice();
        this.quantity = orderDto.getQuantity();
        this.address = orderDto.getAddress();
//        this.userId = orderDto.getUserId();
//        this.bookId = orderDto.getBookId();
        this.cancel = false;

    }
}
