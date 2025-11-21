package com.library.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import java.util.ArrayList;

import com.library.dao.IssueDAO;
import com.library.model.IssuedBook;
import com.library.util.DBConnection;

import java.time.LocalDate;
import java.sql.Date;
public class IssueDAOImpl implements IssueDAO{

    @Override
    public boolean addIssue(IssuedBook issue) {
        String query = "INSERT INTO issued_books(member_id,book_id,issue_date,return_date) VALUES(?,?,?,NULL)";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, issue.getMemberId());
            preparedStatement.setInt(2, issue.getBookId());
            preparedStatement.setDate(3, Date.valueOf(issue.getIssueDate()));

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected>0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public IssuedBook getActiveIssue(int memberId, int bookId) {
        String query = "SELECT * FROM issued_books WHERE member_id = ? AND book_id = ? AND return_date IS NULL";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            preparedStatement.setInt(2, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int member_id = resultSet.getInt("member_id");
                int book_id = resultSet.getInt("book_id");
                LocalDate issueDate = resultSet.getDate("issue_date").toLocalDate();
                return new IssuedBook(book_id, member_id, issueDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IssuedBook> getAllIssuedBooks() {
        List<IssuedBook> allIssuedBooks = new ArrayList<>();
        String query = "SELECT * FROM issued_books";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int issueId = resultSet.getInt("issue_id");
                int memberId = resultSet.getInt("member_id");
                int bookId = resultSet.getInt("book_id");
                LocalDate issueDate = resultSet.getDate("issue_date").toLocalDate();
                LocalDate returnDate = resultSet.getDate("return_date") == null? null: resultSet.getDate("return_date").toLocalDate();
                allIssuedBooks.add(new IssuedBook(issueId, bookId, memberId,issueDate, returnDate));
            }
            return allIssuedBooks;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public IssuedBook getIssueById(int issueId) {
        String query = "SELECT * FROM issued_books WHERE issue_id = ?";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, issueId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new IssuedBook(
                    resultSet.getInt("issue_id"),
                    resultSet.getInt("member_id"),
                    resultSet.getInt("book_id"),
                    resultSet.getDate("issue_date").toLocalDate(),
                    resultSet.getDate("return_date") == null? null: resultSet.getDate("return_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IssuedBook> getIssuedBooksByMemberId(int memberId) {
        List<IssuedBook> issuedBooks = new ArrayList<>();
        String query = "SELECT * FROM issued_books WHERE member_id = ?";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                issuedBooks.add(new IssuedBook(
                    resultSet.getInt("issue_id"),
                    resultSet.getInt("member_id"),
                    resultSet.getInt("book_id"),
                    resultSet.getDate("issue_date").toLocalDate(),
                    resultSet.getDate("return_date") == null ? null : resultSet.getDate("return_date").toLocalDate()));
            }
            return issuedBooks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean returnBook(int issueId) {
        String query = "UPDATE issued_books SET return_date = ? WHERE issue_id = ?";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setInt(2, issueId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
