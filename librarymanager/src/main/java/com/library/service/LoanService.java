package com.library.service;

import com.library.dao.LoanDAO;
import com.library.model.Loan;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date;

public class LoanService {
    private LoanDAO loanDAO;

    public LoanService() {
        this.loanDAO = new LoanDAO();
    }

    public boolean borrowBook(int borrowerId, int bookId) {
        Loan loan = new Loan();

        loan.setBorrowerId(borrowerId);
        loan.setBookId(bookId);

        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(7);

        loan.setBorrowDate(Date.valueOf(borrowDate));
        loan.setReturnDate(Date.valueOf(returnDate));

        return loanDAO.borrowBook(loan);
    }
    public boolean returnBook(int loanId) {
        return loanDAO.returnBook(loanId);
    }

    public List<Loan> getAllLoans() {
        return loanDAO.getAllLoans();
    }
}