package com.example.demo.bridgelabz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bridgelabz.model.BookDetails;

public interface BookDetailsRepository  extends JpaRepository<BookDetails , Integer> {

    Optional<BookDetails> findByBookId(int bookId);

    Optional<BookDetails> findByBookName(String bookName);


}