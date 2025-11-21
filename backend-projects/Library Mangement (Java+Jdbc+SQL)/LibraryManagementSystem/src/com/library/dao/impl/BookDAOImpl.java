package com.library.dao.impl;

import com.library.dao.BookDAO;

import com.library.model.Book;

import java.util.List;

import java.util.ArrayList;

import java.sql.*;

import com.library.util.DBConnection;

public class BookDAOImpl implements BookDAO {

    @Override
    public boolean addBook(Book book) {
        String query = "INSERT INTO books(title,author,total_copies,available_copies) VALUES(?,?,?,?)";

        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getTotalCopies());
            preparedStatement.setInt(4, book.getAvailableCopies());
            int affectedRows = preparedStatement.executeUpdate();
            
            return affectedRows>0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(Book book) {
        String query = "DELETE FROM books WHERE book_id = ?";

        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, book.getBookId());
            int affectedRows = preparedStatement.executeUpdate();
            
            return affectedRows>0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()==false) return new ArrayList<>();
            while(resultSet.next()){
                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int total_copies = resultSet.getInt("total_copies");
                int available_copies = resultSet.getInt("available_copies");
                allBooks.add(new Book(bookId,title, author, total_copies, available_copies));
            }
            return allBooks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book getBookByID(int id) {
        String query = "SELECT * FROM books WHERE book_id = ?";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int total_copies = resultSet.getInt("total_copies");
                int available_copies = resultSet.getInt("available_copies");
                return new Book(bookId,title, author, total_copies, available_copies);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        String query = "SELECT * FROM books WHERE title LIKE ?";
        List<Book> allBooks = new ArrayList<>();
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()==false) return new ArrayList<>();
                while(resultSet.next()){
                    int bookId = resultSet.getInt("book_id");
                    String bookTitle = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    int total_copies = resultSet.getInt("total_copies");
                    int available_copies = resultSet.getInt("available_copies");
                    allBooks.add(new Book(bookId,bookTitle, author, total_copies, available_copies));
                }
                return allBooks;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    public boolean updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, total_copies = ?, available_copies = ? WHERE book_id = ?";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getTotalCopies());
            preparedStatement.setInt(4, book.getAvailableCopies());
            preparedStatement.setInt(5, book.getBookId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows>0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
