package com.library.model;

public class Borrower {
    private int borrowerId;
    private String fullName;
    private String phone;
    private String email;

    public Borrower() {}

    public Borrower(int borrowerId, String fullName, String phone, String email) {
        this.borrowerId = borrowerId;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public int getBorrowerId() { return borrowerId; }
    public void setBorrowerId(int borrowerId) { this.borrowerId = borrowerId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}