package com.library.view.login;
import com.library.view.admin.AdminDashboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class LoginFrame extends JFrame {
    private JTextField txtUsername = new JTextField(20);
    private JPasswordField txtPassword = new JPasswordField(20);
    private JButton btnLogin = new JButton("Đăng Nhập");
    public LoginFrame() {
        setTitle("Đăng Nhập Hệ Thống");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ra giữa màn hình
        initComponents();
        setVisible(true);
    }
    private void initComponents() {
        setLayout(new GridLayout(3, 2, 10, 10));
        add(new JLabel("Tài khoản:")); add(txtUsername);
        add(new JLabel("Mật khẩu:"));  add(txtPassword);
        add(new JLabel(""));           add(btnLogin);
        btnLogin.addActionListener(e -> handleLogin());
        getRootPane().setDefaultButton(btnLogin); // Enter là tự ấn nút
    }
    private void handleLogin() {
        String user = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());
        // Logic kiểm tra (Giống LoginController cũ)
        if ("admin".equals(user) && "123".equals(pass)) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
            new AdminDashboard(); // Mở màn hình chính
            dispose(); // Đóng màn hình Login
        } else {
            JOptionPane.showMessageDialog(this, "Sai thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        new LoginFrame(); // Chạy test thử
    }
}
