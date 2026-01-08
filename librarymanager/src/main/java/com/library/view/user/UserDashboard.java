package com.library.view.user;
import com.library.model.User;

import javax.swing.*;
public class UserDashboard extends JFrame {
    private User user;
    private JTabbedPane tabbedPane = new JTabbedPane();
    public UserDashboard(User user) {
        setTitle("Hệ Thống Thư Viện - Dành Cho Bạn Đọc");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Đóng thì chỉ tắt cửa sổ này, không tắt cả app
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }
    private void initComponents() {
        // Tab 1: Panel Mượn Sách (Code ở trên)
        tabbedPane.addTab("Mượn Sách Mới", new BorrowPanel());
        // Tab 2: Lịch Sử (Code ở dưới)
        tabbedPane.addTab("Lịch Sử Của Tôi", new MyBorrowHistory());
        add(tabbedPane);
    }
}
