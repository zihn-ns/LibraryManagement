package com.library.model;
import java.sql.Date;

public class Loan {
    private int loanId;
    private int bookId;
    private int borrowerId;
    private Date borrowDate;
    private Date returnDate;
    private String status;

    // Phải có các hàm này thì LoanDAO mới không báo lỗi
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getBorrowerId() { return borrowerId; }
    public void setBorrowerId(int borrowerId) { this.borrowerId = borrowerId; }
    
    // Thêm các Getter/Setter cho các thuộc tính còn lại...
}