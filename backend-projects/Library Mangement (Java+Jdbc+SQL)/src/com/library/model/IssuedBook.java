package com.library.model;

import java.time.LocalDate;

public class IssuedBook {
    private int issuedId;
    private int bookId;
    private int memberId;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssuedBook(int bookId,int memberId,LocalDate issueDate){
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        returnDate = null;
    }

    public IssuedBook(int issuedId,int bookId,int memberId,LocalDate issueDate,LocalDate returnDate){
        this.issuedId = issuedId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getIssuedId() {
        return issuedId;
    }

    public void setIssuedId(int issuedId) {
        this.issuedId = issuedId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString(){
        return "Issued ID: "+issuedId+
        ", Book ID: "+bookId+
        ", Member ID: "+memberId+
        ", Issue Date: "+issueDate+
        ", Return Date: "+returnDate;
    }
}
