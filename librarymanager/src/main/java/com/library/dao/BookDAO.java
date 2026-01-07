package com.library.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.library.database.DBConnection;
import com.library.model.Book;

public class BookDAO implements IBookDAO {

    // ... (Giữ nguyên hàm getAllBooks của bạn) ...
    @Override
    public List<Book> getAllBooks() {
        // Copy lại code getAllBooks bạn đã chạy thành công ở trên vào đây
        List<Book> list = new ArrayList<>();
        String sql = "SELECT b.*, a.author_name, c.category_name FROM books b " +
                     "JOIN authors a ON b.author_id = a.author_id " +
                     "JOIN categories c ON b.category_id = c.category_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Book b = new Book(
                    rs.getInt("book_id"), rs.getString("title"),
                    rs.getInt("author_id"), rs.getInt("category_id"),
                    rs.getInt("available_copies")
                );
                b.setAuthorName(rs.getString("author_name"));
                b.setCategoryName(rs.getString("category_name"));
                list.add(b);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ... (Giữ nguyên hàm addBook của bạn) ...
    @Override
    public boolean addBook(Book b) {
        String sql = "INSERT INTO books (title, author_id, category_id, available_copies) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getTitle());
            ps.setInt(2, b.getAuthorId());
            ps.setInt(3, b.getCategoryId());
            ps.setInt(4, b.getAvailableCopies());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm sách: " + e.getMessage());
            return false;
        }
    }

    // --- MỚI: TRIỂN KHAI THÊM TÁC GIẢ & LẤY ID ---
    @Override
    public int addAuthor(String name) {
        String sql = "INSERT INTO authors (author_name) VALUES (?)";
        // Tham số thứ 2 là RETURN_GENERATED_KEYS để lấy ID vừa tạo
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, name);
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Trả về ID mới (ví dụ: 5)
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi thêm tác giả: " + e.getMessage());
        }
        return -1; // Trả về -1 nếu lỗi
    }

    // --- MỚI: TRIỂN KHAI THÊM THỂ LOẠI & LẤY ID ---
    @Override
    public int addCategory(String name) {
        String sql = "INSERT INTO categories (category_name) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, name);
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Trả về ID mới
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi thêm thể loại: " + e.getMessage());
        }
        return -1;
    }
    
    // (Giữ hàm searchBooks rỗng hoặc như cũ của bạn)
    @Override public List<Book> searchBooks(String t, String a, String c) { return new ArrayList<>(); }

    // --- HÀM TEST QUY TRÌNH MỚI ---
    public static void main(String[] args) {
        BookDAO dao = new BookDAO();
        System.out.println("--- BẮT ĐẦU TEST THÊM DỮ LIỆU LIÊN HOÀN ---");

        // BƯỚC 1: Thêm Tác giả mới vào bảng authors
        String newAuthorName = "Nguyen Nhat Anh " + System.currentTimeMillis(); // Thêm số để không trùng tên khi test nhiều lần
        int newAuthorId = dao.addAuthor(newAuthorName);
        
        // BƯỚC 2: Thêm Thể loại mới vào bảng categories
        String newCategoryName = "Truyen Dai " + System.currentTimeMillis();
        int newCategoryId = dao.addCategory(newCategoryName);

        // Kiểm tra xem có tạo được ID không
        if (newAuthorId != -1 && newCategoryId != -1) {
            System.out.println("✅ Đã tạo Tác giả ID: " + newAuthorId);
            System.out.println("✅ Đã tạo Thể loại ID: " + newCategoryId);

            // BƯỚC 3: Thêm Sách với ID vừa tạo được
            Book newBook = new Book("Mat Biec (New Version)", newAuthorId, newCategoryId, 50);
            
            if (dao.addBook(newBook)) {
                System.out.println("✅✅✅ THÀNH CÔNG: Đã thêm sách mới kèm Tác giả và Thể loại mới!");
            } else {
                System.err.println("❌ Lỗi khi thêm sách.");
            }
        } else {
            System.err.println("❌ Lỗi: Không thể tạo tác giả hoặc thể loại.");
        }

        // Kiểm tra lại danh sách
        System.out.println("\n--- DANH SÁCH CẬP NHẬT ---");
        for (Book b : dao.getAllBooks()) {
            System.out.println("Book: " + b.getTitle() + " | Author: " + b.getAuthorName() + " | Category: " + b.getCategoryName());
        }
    }
}