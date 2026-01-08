package com.library.dao;

import com.library.database.DBConnection;
import com.library.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Hàm Login quan trọng nhất
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Đăng nhập thất bại
    }

    // Các hàm khác (Optional - để sau này dùng)
    public List<User> getAllUsers() {
        // ... (Bạn có thể để trống hoặc implement sau)
        return new ArrayList<>();
    }

    public boolean addUser(User u) { return false; }
    public boolean updateUser(User u) { return false; }
    public boolean deleteUser(int id) { return false; }
}
