package com.project.GoodReads.controller;

import com.project.GoodReads.service.BookService;
import com.project.GoodReads.model.Book;
import com.project.GoodReads.service.BookH2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController{
    BookService bookService = new BookService();
    @Autowired
    BookH2Service bookH2Service;

    @GetMapping ("/books")
    public ArrayList<Book> getBooks() {
        return bookH2Service.getBooks();
    }

    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable("bookId") int bookId){
        return bookService.getBookById(bookId);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        return bookH2Service.addBook(book);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable("bookId") int bookId,@RequestBody Book book){
        return bookH2Service.updateBook(bookId, book);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId){
         bookH2Service.deleteBook(bookId);
    }
}