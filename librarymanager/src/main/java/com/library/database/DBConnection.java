package com.library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/library_db"
                    + "?useSSL=false"
                    + "&serverTimezone=UTC"
                    + "&allowPublicKeyRetrieval=true";

    private static final String USER = "root";
    private static final String PASS = "Phatđ́ăă"; // ← MẬT KHẨU MYSQL

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
    }

    // Test nhanh kết nối
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("✅ KẾT NỐI MYSQL THÀNH CÔNG!");
        } catch (SQLException e) {
            System.err.println("❌ KẾT NỐI MYSQL THẤT BẠI!");
            e.printStackTrace();
        }
    }
}
