package com.library.model;

public class Book {
    private int bookId;         // book_id
    private String title;       // title
    private int authorId;       // author_id
    private int categoryId;     // category_id
    private int availableCopies;// available_copies
    
    // Thuộc tính bổ sung để hiển thị tên Tác giả/Thể loại
    private String authorName;
    private String categoryName;

    // Constructor không đối số
    public Book() {}

    // Constructor dùng để thêm mới (không cần bookId vì nó tự tăng)
    public Book(String title, int authorId, int categoryId, int availableCopies) {
        this.title = title;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.availableCopies = availableCopies;
    }

    // Constructor đầy đủ để lấy dữ liệu từ database
    public Book(int bookId, String title, int authorId, int categoryId, int availableCopies) {
        this.bookId = bookId;
        this.title = title;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.availableCopies = availableCopies;
    }

    // --- Getters và Setters (Phải có đầy đủ) ---
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public int getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}