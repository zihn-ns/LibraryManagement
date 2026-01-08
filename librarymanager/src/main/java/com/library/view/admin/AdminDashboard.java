package com.library.view.admin;

import javax.swing.*;

import com.library.view.login.LoginFrame;

import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Library Management - Admin Dashboard");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("ADMIN DASHBOARD", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(5, 1, 15, 15));

        JButton btnManageBooks = new JButton("📚 Manage Books");
        JButton btnManageUsers = new JButton("👤 Manage Users");
        JButton btnBorrowReturn = new JButton("🔄 Borrow / Return");
        JButton btnLogout = new JButton("🚪 Logout");

        menuPanel.add(btnManageBooks);
        menuPanel.add(btnManageUsers);
        menuPanel.add(btnBorrowReturn);
        menuPanel.add(btnLogout);

        add(menuPanel, BorderLayout.CENTER);

        // ========= EVENTS =========
        btnManageBooks.addActionListener(e -> {
            new ManageBookFrame();
            dispose();
        });

        btnManageUsers.addActionListener(e -> {
            new ManageUserFrame();   // bạn sẽ tạo
            dispose();
        });

        btnBorrowReturn.addActionListener(e -> {
            new ManageBorrowFrame(); // bạn sẽ tạo
            dispose();
        });

        btnLogout.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminDashboard::new);
    }
}

