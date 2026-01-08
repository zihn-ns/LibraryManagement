package com.library.dao;

import com.library.database.DBConnection;
import java.sql.*;

public class ReturnBookDAO {

    /**
     * Trả sách theo loanId
     */
    public boolean returnBook(int loanId) {

        String getLoanSql =
            "SELECT book_id, status FROM loans WHERE id = ?";

        String updateLoanSql =
            "UPDATE loans SET return_date = CURRENT_DATE, status = 'Returned' WHERE id = ?";

        String updateBookSql =
            "UPDATE books SET quantity = quantity + 1 WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // transaction

            // 1️⃣ Kiểm tra loan
            PreparedStatement ps = conn.prepareStatement(getLoanSql);
            ps.setInt(1, loanId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ Không tìm thấy loan");
                return false;
            }

            if ("Returned".equals(rs.getString("status"))) {
                System.out.println("❌ Sách đã được trả rồi");
                return false;
            }

            int bookId = rs.getInt("book_id");

            // 2️⃣ Update loans
            ps = conn.prepareStatement(updateLoanSql);
            ps.setInt(1, loanId);
            ps.executeUpdate();

            // 3️⃣ Cộng lại sách
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
