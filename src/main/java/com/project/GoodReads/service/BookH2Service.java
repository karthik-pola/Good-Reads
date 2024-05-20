package com.project.GoodReads.service;
import com.project.GoodReads.model.Book;
import com.project.GoodReads.model.BookRowMapper;
import com.project.GoodReads.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookH2Service implements BookRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Book> getBooks() {
        List<Book> bookList = db.query("select * from book", new BookRowMapper());
        ArrayList<Book> books = new ArrayList<>(bookList);
        return books;
    }

    @Override
    public Book getBookById(int bookId) {
        return db.queryForObject("select * from book where id=?" , new BookRowMapper() , bookId);
//        return book;
    }

    @Override
    public Book addBook(Book book) {
        db.update("insert into book(name , imageUrl) values(?,?)", book.getName(), book.getImageUrl());

//        Book savedBook =  db.queryForObject("select * from book where name=? and imageUrl=?", new BookRowMapper(), book.getName(), book.getImageUrl());
//        System.out.println(db.queryForObject("select * from book where name=? and imageUrl=?", new BookRowMapper(), book.getName(), book.getImageUrl()));
//        return null;

        Book savedBook = db.queryForObject("select * from book where name = ? AND imageUrl = ? ORDER BY id DESC LIMIT 1",
                new BookRowMapper(), book.getName(), book.getImageUrl());


        return savedBook;
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        // Check and update the 'name' field if it's not null
        if (book.getName() != null) {
            db.update("UPDATE book SET name = ? WHERE id = ?", book.getName(), bookId);
        }
        // Check and update the 'imageUrl' field if it's not null
        if (book.getImageUrl() != null) {
            db.update("UPDATE book SET imageUrl = ? WHERE id = ?", book.getImageUrl(), bookId);
        }
        // Retrieve and return the updated book record by its ID
        return getBookById(bookId);
    }


    @Override
    public void deleteBook(int bookId) {
        db.update("delete from book where id = ?", bookId);
    }
}