package com.library.dao;

import com.library.database.DBConnection;
import com.library.model.Borrower;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDAO {
    // Lấy danh sách để hiển thị lên bảng (View)
    public List<Borrower> getAllBorrowers() {
        List<Borrower> list = new ArrayList<>();
        String sql = "SELECT * FROM Borrowers";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                list.add(new Borrower(
                    rs.getInt("borrower_id"),
                    rs.getString("full_name"),
                    rs.getString("phone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Thêm người mượn mới
    public boolean addBorrower(Borrower b) {
        String sql = "INSERT INTO borrowers (full_name, phone, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getFullName());
            ps.setString(2, b.getPhone());
            ps.setString(3, b.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
    BorrowerDAO dao = new BorrowerDAO();
    Borrower testB = new Borrower(0, "User Test", "0987", "test@gmail.com");
    if(dao.addBorrower(testB)) {
        System.out.println("✅ BorrowerDAO: Thêm người dùng thành công!");
    } else {
        System.err.println("❌ BorrowerDAO: Thất bại!");
    }
}

    public List<Borrower> getAllBorrows() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllBorrows'");
    }

    public boolean borrowBook(Borrower b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrowBook'");
    }

    public boolean returnBook(int borrowId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'returnBook'");
    }
}