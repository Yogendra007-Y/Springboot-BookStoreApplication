package com.example.demo.bridgelabz.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bridgelabz.dto.BookDetailsDto;
import com.example.demo.bridgelabz.dto.ResponseDTO;
import com.example.demo.bridgelabz.model.BookDetails;
import com.example.demo.bridgelabz.service.IBookDetailsService;
import com.example.demo.bridgelabz.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bookservice")
@Slf4j
public class BookDetailsController {

    @Autowired
    private IBookDetailsService bookService;



    @Autowired
    private TokenUtil tokenUtil;

    @RequestMapping(value = {"", "/", "/getbooks"})
    public ResponseEntity<ResponseDTO> getAllBooks(@RequestHeader(name = "token") String token ) {
        List<BookDetails> allBooks = bookService.showAllBooks(token);
        ResponseDTO dto = new ResponseDTO("All Books Retrieved successfully:", allBooks);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getBookByName/{bookName}")
    public ResponseEntity<ResponseDTO> getOneBookByName(@RequestHeader(name = "token") String token,@PathVariable String bookName)
    {
        BookDetails getOneBook = bookService.getBookByName(token,bookName);
        ResponseDTO dto = new ResponseDTO("Book retrieved successfully"+bookName, getOneBook);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/getBook/{bookId}")
    public ResponseEntity<ResponseDTO> getOneBook(@RequestHeader(name = "token") String token,@PathVariable int bookId)
    {
        BookDetails getOneBook = bookService.getBookById(token,bookId);
        ResponseDTO dto = new ResponseDTO("Book retrieved successfully"+bookId, getOneBook);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<ResponseDTO> addBook(@RequestHeader(name = "token") String token,@RequestBody BookDetailsDto bookDto) {
        BookDetails addBook = bookService.addBook(token,bookDto);
        ResponseDTO dto = new ResponseDTO("Book added successfully ", tokenUtil.createToken(addBook.getBookId()) );
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookData(@RequestHeader(name = "token") String token,@PathVariable("bookId") int bookId,
                                                      @Valid @RequestBody BookDetailsDto bookDTO) {
        BookDetails updateBook = bookService.updateBook(token,bookId, bookDTO);
        log.debug(" After Update " + updateBook.toString());
        ResponseDTO response = new ResponseDTO("Updated  for" + bookId, updateBook);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    @PutMapping("/updateByName/{bookName}")
    public ResponseEntity<ResponseDTO> updateBookDataByName(@RequestHeader(name = "token") String token,@PathVariable("bookName") String bookName,
                                                            @Valid @RequestBody BookDetailsDto bookDTO) {
        BookDetails updateBook = bookService.updateBookByName(token,bookName, bookDTO);
        log.debug(" After Update " + updateBook.toString());
        ResponseDTO response = new ResponseDTO("Updated  for Data " + bookName, updateBook);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBook(@RequestHeader(name = "token") String token,@PathVariable("bookId") int bookId) {
        bookService.deleteBook(token,bookId);
        ResponseDTO response = new ResponseDTO("Delete call success for id ", "deleted id:" + bookId);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    @PutMapping("/updatequantity/{bookId}/{bookQuantity}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@RequestHeader(name = "token") String token,@PathVariable int bookId, @PathVariable int bookQuantity) {
        BookDetails updateBookQuantity = bookService.updateBookQuantity(token,bookId, bookQuantity);
        ResponseDTO response = new ResponseDTO("Book Quantity Update is success for id " + bookId, updateBookQuantity);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }


    @PutMapping("/updateprice/{bookId}/{bookPrice}")
    public ResponseEntity<ResponseDTO> updateBookPrice(@RequestHeader(name = "token") String token,@PathVariable int bookId, @PathVariable int bookPrice) {
        BookDetails updateBookPrice = bookService.updateBookPrice(token,bookId, bookPrice);
        ResponseDTO response = new ResponseDTO("Book Price Update is success for id " + bookId, updateBookPrice);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
}