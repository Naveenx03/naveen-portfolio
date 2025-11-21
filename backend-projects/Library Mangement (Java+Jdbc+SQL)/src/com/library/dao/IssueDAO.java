package com.library.dao;

import java.util.List;

import com.library.model.IssuedBook;

public interface IssueDAO {

    boolean addIssue(IssuedBook issue);

    boolean returnBook(int issueId);

    IssuedBook getIssueById(int issueId);

    IssuedBook getActiveIssue(int memberId, int bookId);

    List<IssuedBook> getAllIssuedBooks();

    List<IssuedBook> getIssuedBooksByMemberId(int memberId);

} 
