package com.library.view.admin;
import com.library.view.user.BorrowPanel; // Cái Panel Mượn Sách mình tạo ở bài trước
import javax.swing.*;
import java.awt.*;
public class AdminDashboard extends JFrame {
    private JTabbedPane tabbedPane = new JTabbedPane();
    public AdminDashboard() {
        setTitle("Quản Lý Thư Viện - Admin");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        setVisible(true);
    }
    private void initComponents() {
        // Tab 1: Quản lý Sách (JTable cũ của bạn)
        // tabbedPane.addTab("Quản Lý Sách", new ManageBookPanel()); 
        
        // Tab 2: Mượn Sách (Cái BorrowPanel mình vừa tạo từ BorrowController)
        tabbedPane.addTab("Mượn Sách", new BorrowPanel());
        // Tab 3: Lịch sử (Port từ HistoryController)
        tabbedPane.addTab("Lịch Sử Trả", new JPanel()); // Để trống chờ làm
        add(tabbedPane);
    }
}