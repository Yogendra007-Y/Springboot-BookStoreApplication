package com.example.demo.bridgelabz.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "Cart")
public @Data
class CartData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserRegistrationData user;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private BookDetails book;

    private int quantity;


    public CartData(UserRegistrationData user, BookDetails book, int quantity) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }

    public CartData() {

    }
}
