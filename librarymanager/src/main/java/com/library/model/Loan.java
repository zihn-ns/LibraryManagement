package com.library.model;

import java.sql.Date;

public class Loan {
    private int loanId;
    private int bookId;
    private int borrowerId;
    private Date borrowDate;
    private Date returnDate;
    private String status;

    public Loan() {}

    public Loan(int bookId, int borrowerId) {
        this.bookId = bookId;
        this.borrowerId = borrowerId;
    }

    // Getters and Setters
    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getBorrowerId() { return borrowerId; }
    public void setBorrowerId(int borrowerId) { this.borrowerId = borrowerId; }

    public Date getBorrowDate() { return borrowDate; }
    public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }

    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}