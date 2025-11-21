package com.library.service;

import com.library.model.*;

import com.library.dao.*;

import com.library.dao.impl.*;

import java.sql.Connection;

import java.sql.SQLException;

import com.library.util.DBConnection;

import java.time.LocalDate;
import java.util.List;

public class LibraryService {

    private BookDAO bookDAO;
    private MemberDAO memberDAO;
    private IssueDAO issueDAO;

    public LibraryService(){
        bookDAO = new BookDAOImpl();
        memberDAO = new MemberDAOImpl();
        issueDAO = new IssueDAOImpl();
    }

    public String issueBook(int bookId, int memberId){
        try(Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            Book book = bookDAO.getBookByID(bookId);
            if(book==null) return "Book not found !!";

            if(book.getAvailableCopies()<=0) return "No copies available !!";

            Member member = memberDAO.getMemberById(memberId);
            if(member==null) return "Member not found !!";

            IssuedBook active = issueDAO.getActiveIssue(memberId, bookId);
            if(active!=null) return"This member already borrowed the book and has not returned it yet";

            IssuedBook issue = new IssuedBook(bookId, memberId, LocalDate.now());
            boolean issueAdded = issueDAO.addIssue(issue);
            if(!issueAdded){
                conn.rollback();
                return "Failed to issue book";
            }

            book.setAvailableCopies(book.getAvailableCopies()-1);
            boolean updated = bookDAO.updateBook(book);
            if(!updated){
                conn.rollback();
                return "Failed to update book record !!";
            }

            conn.commit();
            return "Book issued successfully !!";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Internal Error Occurred !!";
        }
    }

    public String returnBook(int issueId){
        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);

            IssuedBook issue = issueDAO.getIssueById(issueId);
            if(issue==null) return "Issue record not found !!";

            if(issue.getReturnDate() != null) return "Book is already returned !!";

            boolean returned = issueDAO.returnBook(issueId);
            if(!returned) return "Failed to return book";
            
            Book book = bookDAO.getBookByID(issue.getBookId());
            book.setAvailableCopies(book.getAvailableCopies()+1);
            boolean updated = bookDAO.updateBook(book);
            if(!updated){
                conn.rollback();
                return "Failed to update book record";
            }

            conn.commit();

            return "Book returned successfully !!";
            

        } catch (SQLException e) {
            e.printStackTrace();
            return "Internal Error Occured !!";
        }
    }

    public String addBook(String title, String author, int totalCopies){

        if(title==null || title.isBlank()) return "Title cannot be empty !!";
        if(author==null || author.isBlank()) return "Author cannot be empty !!";
        if(totalCopies<=0) return "Total copies must be greater than zero. !!";

        Book book = new Book(title, author, totalCopies, totalCopies);
        boolean success = bookDAO.addBook(book);
        return success ? "Book added successfully !!" : "Failed to add book !!";
    }
    
    public String updateBook(int bookId, String title, String author, int totalCopies, int availableCopies){
        
        Book book = bookDAO.getBookByID(bookId);
        if(book==null) return "Book not found !!";
        if(title==null || title.isBlank()) return "Title cannot be empty !!";
        if(author==null || author.isBlank()) return "Author cannot be empty !!";
        if(totalCopies<=0) return "Total copies must be greater than zero. !!";
        if(availableCopies>totalCopies || availableCopies<0) return "Available copies must be between 0 and total copies.";

        Book updatedBook = new Book(bookId,title, author, totalCopies, availableCopies);
        boolean success = bookDAO.updateBook(updatedBook);

        return success ? "Book updated successfully !!" : "Failed to update book !!";
    }

    public String deleteBook(int id){

        Book book = bookDAO.getBookByID(id);
        if(book==null) return "Book not found !!";

        boolean success = bookDAO.deleteBook(book);

        return success ? "Book record deleted successfully !!" : "Failed to delete book record !!";
    }

    public List<Book> getAllBooks(){
        return bookDAO.getAllBooks();
    }
    
    public String addMember(String name, String email, String phone){

        if(name==null || name.isBlank()) return "Name connot be empty !!";
        if(email==null || email.isBlank()) return "Email connot be empty !!";
        if(phone==null || phone.isBlank()) return "Phone number connot be empty !!";

        Member member = new Member(name, email, phone);
        boolean success = memberDAO.addMember(member);

        return success ? "Member added successfully !!" : "Failed to add Member !!";
    }

    public String updateMember(int memberId, String name, String email, String phone){

        Member member = memberDAO.getMemberById(memberId);
        if(member==null) return "Member not found !!";
        if(name==null || name.isBlank()) return "Name connot be empty !!";
        if(email==null || email.isBlank()) return "Email connot be empty !!";
        if(phone==null || phone.isBlank()) return "Phone number connot be empty !!";

        Member updatedMember = new Member(memberId, name, email, phone);
        boolean success = memberDAO.updateMember(updatedMember);
        return success ? "Member updated successfully !!" : "Failed to update Member !!";
    }

    public String deleteMember(int id){

        Member member = memberDAO.getMemberById(id);
        if(member==null) return "Member not found !!";

        boolean success = memberDAO.deleteMember(id);
        return success ? "Member deleted successfully !!" : "Failed to delete Member !!";
    }

    public List<Member> getAllMembers(){
        return memberDAO.getAllMembers();
    }
    
    public List<IssuedBook> getAllIssuedBooks(){
        return issueDAO.getAllIssuedBooks();
    }

    public List<IssuedBook> getIssuedBooksByMemberId(int memberId){
        return issueDAO.getIssuedBooksByMemberId(memberId);
    }

}
