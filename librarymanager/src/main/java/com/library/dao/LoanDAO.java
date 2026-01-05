package com.library.dao;
import com.library.database.DBConnection;
import com.library.model.Loan;
import java.sql.*;

public class LoanDAO {
    public boolean borrowBook(Loan loan) {
        String query = "INSERT INTO loans (book_id, borrower_id, borrow_date, status) VALUES (?, ?, CURRENT_DATE, 'Borrowed')";
        // Thêm logic UPDATE status sách trong bảng books ở đây
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, loan.getBookId());
            ps.setInt(2, loan.getBorrowerId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}