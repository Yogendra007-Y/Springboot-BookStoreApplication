package com.example.demo.bridgelabz.service;

import java.util.List;

import com.example.demo.bridgelabz.dto.BookDetailsDto;
import com.example.demo.bridgelabz.model.BookDetails;

public interface IBookDetailsService {

    List<BookDetails> showAllBooks(String token);

    BookDetails addBook(String token,BookDetailsDto bookDto);
    BookDetails getBookById(String token,int bookId);
    BookDetails getBookByName(String token,String bookName);
    BookDetails updateBook(String token,int bookId, BookDetailsDto bookDTO);

    BookDetails updateBookByName(String token,String bookName, BookDetailsDto bookDTO);


    void deleteBook(String token,int bookId);

    BookDetails updateBookQuantity(String token,int bookId, int bookQuantity);

    BookDetails updateBookPrice(String token,int bookId, int bookPrice);
}