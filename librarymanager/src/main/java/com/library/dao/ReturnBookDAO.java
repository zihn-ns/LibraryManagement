package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.database.DBConnection;

public class ReturnBookDAO {

    public boolean returnBook(int loanId) {

        // ✅ ĐÚNG: loan_id
        String getLoanSql =
            "SELECT book_id, status FROM loans WHERE loan_id = ?";

        String updateLoanSql =
            "UPDATE loans SET return_date = CURRENT_DATE, status = 'Returned' WHERE loan_id = ?";

        // books dùng id → OK
        String updateBookSql =
            "UPDATE books SET quantity = quantity + 1 WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(getLoanSql);
            ps.setInt(1, loanId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Khong tim thay loan");
                return false;
            }

            if ("Returned".equals(rs.getString("status"))) {
                System.out.println("Sach da duoc tra roi");
                return false;
            }

            int bookId = rs.getInt("book_id");

            ps = conn.prepareStatement(updateLoanSql);
            ps.setInt(1, loanId);
            ps.executeUpdate();

            ps = conn.prepareStatement(updateBookSql);
            ps.setInt(1, bookId);
            ps.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
