package com.library.dao;

import com.library.model.Book;
import java.util.List;

public interface IBookDAO {
    List<Book> getAllBooks();
    
    // Giữ nguyên hàm thêm sách
    boolean addBook(Book b); 
    
    // --- MỚI: Thêm Author/Category và trả về ID vừa tạo ---
    int addAuthor(String name);
    int addCategory(String name);

    List<Book> searchBooks(String title, String author, String category);
}