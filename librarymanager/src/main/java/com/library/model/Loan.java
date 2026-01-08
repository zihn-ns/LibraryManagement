package com.library.model;

import java.sql.Date;

public class Loan {
    private int loanId;
    private int bookId;
    private int borrowerId;
    private Date borrowDate;
    private Date returnDate; // Dùng để ghi nhận ngày trả thực tế
    private String status;

    // 1. Constructor không đối số (No-arg constructor)
    public Loan() {
    }

    // 2. Constructor đầy đủ đối số
    public Loan(int loanId, int bookId, int borrowerId, Date borrowDate, Date returnDate, String status) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // 3. Getter và Setter cho LoanId
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    // 4. Getter và Setter cho BookId
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    // 5. Getter và Setter cho BorrowerId
    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    // 6. Getter và Setter cho BorrowDate
    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    // 7. Getter và Setter cho ReturnDate
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    // 8. Getter và Setter cho Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}