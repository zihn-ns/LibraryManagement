package com.library.dao;

import com.library.database.DBConnection;
import com.library.model.Loan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    // Mượn sách: Kiểm tra số lượng -> Trừ kho -> Tạo phiếu mượn (Transaction)
    public boolean borrowBook(Loan loan) {
        String checkStockSql = "SELECT available_copies FROM books WHERE book_id = ?";
        String updateBookSql = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";
        String insertLoanSql = "INSERT INTO loans (book_id, borrower_id, borrow_date, status) VALUES (?, ?, CURRENT_DATE, 'Borrowed')";

        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement updateStmt = null;
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Bắt đầu Transaction

            // 1. Kiểm tra tồn kho
            checkStmt = conn.prepareStatement(checkStockSql);
            checkStmt.setInt(1, loan.getBookId());
            rs = checkStmt.executeQuery();

            if (rs.next()) {
                int available = rs.getInt("available_copies");
                if (available <= 0) {
                    System.err.println("❌ Sách đã hết, không thể mượn!");
                    conn.rollback();
                    return false;
                }
            } else {
                System.err.println("❌ Không tìm thấy sách ID: " + loan.getBookId());
                conn.rollback();
                return false;
            }

            // 2. Trừ tồn kho
            updateStmt = conn.prepareStatement(updateBookSql);
            updateStmt.setInt(1, loan.getBookId());
            updateStmt.executeUpdate();

            // 3. Tạo phiếu mượn
            insertStmt = conn.prepareStatement(insertLoanSql);
            insertStmt.setInt(1, loan.getBookId());
            insertStmt.setInt(2, loan.getBorrowerId());
            int rows = insertStmt.executeUpdate();

            if (rows > 0) {
                conn.commit(); // Thành công
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            return false;
        } finally {
            // Đóng resources thủ công để an toàn
            try {
                if (rs != null) rs.close();
                if (checkStmt != null) checkStmt.close();
                if (updateStmt != null) updateStmt.close();
                if (insertStmt != null) insertStmt.close();
                if (conn != null) { conn.setAutoCommit(true); conn.close(); }
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // Trả sách: Cập nhật phiếu -> Cộng kho (Transaction)
    public boolean returnBook(int loanId) {
        String getLoanSql = "SELECT book_id, status FROM loans WHERE loan_id = ?";
        String updateLoanSql = "UPDATE loans SET return_date = CURRENT_DATE, status = 'Returned' WHERE loan_id = ?";
        String updateBookSql = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?";

        Connection conn = null;
        PreparedStatement getStmt = null;
        PreparedStatement updateLoanStmt = null;
        PreparedStatement updateBookStmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Lấy thông tin phiếu để biết Book ID
            getStmt = conn.prepareStatement(getLoanSql);
            getStmt.setInt(1, loanId);
            rs = getStmt.executeQuery();

            int bookId = -1;
            if (rs.next()) {
                if ("Returned".equalsIgnoreCase(rs.getString("status"))) {
                    System.err.println("⚠️ Phiếu này đã trả rồi!");
                    conn.rollback();
                    return false;
                }
                bookId = rs.getInt("book_id");
            } else {
                System.err.println("❌ Không tìm thấy Loan ID: " + loanId);
                conn.rollback();
                return false;
            }

            // 2. Cập nhật phiếu mượn
            updateLoanStmt = conn.prepareStatement(updateLoanSql);
            updateLoanStmt.setInt(1, loanId);
            updateLoanStmt.executeUpdate();

            // 3. Cộng tồn kho
            updateBookStmt = conn.prepareStatement(updateBookSql);
            updateBookStmt.setInt(1, bookId);
            updateBookStmt.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (getStmt != null) getStmt.close();
                if (updateLoanStmt != null) updateLoanStmt.close();
                if (updateBookStmt != null) updateBookStmt.close();
                if (conn != null) { conn.setAutoCommit(true); conn.close(); }
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // Lấy danh sách phiếu mượn
    public List<Loan> getAllLoans() {
        List<Loan> list = new ArrayList<>();
        String sql = "SELECT * FROM loans ORDER BY loan_id DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Loan l = new Loan();
                l.setLoanId(rs.getInt("loan_id"));
                l.setBookId(rs.getInt("book_id"));
                l.setBorrowerId(rs.getInt("borrower_id"));
                l.setBorrowDate(rs.getDate("borrow_date"));
                l.setReturnDate(rs.getDate("return_date"));
                l.setStatus(rs.getString("status"));
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}