package com.library.dao;

import com.library.model.Book;

import java.util.List;

public interface BookDAO {

    boolean addBook(Book book);
    
    boolean updateBook(Book book);

    boolean deleteBook(Book book);

    Book getBookByID(int id);

    List<Book> getAllBooks();

    List<Book> searchBooksByTitle(String title);

} 