package com.library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Lớp quản lý kết nối đến cơ sở dữ liệu MySQL trên XAMPP
 */
public class DBConnection {
    // Thông tin cấu hình kết nối
    // 3306 là cổng mặc định của MySQL trên XAMPP
    // LibraryManagement là tên database bạn tạo trong phpMyAdmin
    private static final String URL = "jdbc:mysql://localhost:3306/library_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = ""; // XAMPP mặc định mật khẩu trống

    /**
     * Phương thức thiết lập kết nối
     * @return Connection đối tượng kết nối
     * @throws SQLException lỗi nếu không kết nối được
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Nạp Driver (từ thư viện Maven đã tải)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy Driver MySQL: " + e.getMessage());
            throw new SQLException(e);
        }
    }

    // Hàm kiểm tra nhanh kết nối (có thể xóa sau khi test xong)
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Chúc mừng! Bạn đã kết nối thành công tới MySQL trên XAMPP.");
            }
        } catch (SQLException e) {
            System.err.println("Kết nối thất bại! Hãy kiểm tra XAMPP đã Start MySQL chưa.");
            e.printStackTrace();
        }
    }
}