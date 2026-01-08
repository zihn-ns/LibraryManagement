package com.library.view.login;

import javax.swing.*;

import com.library.dao.UserDAO;
import com.library.model.User;
import com.library.view.admin.AdminDashboard;
import com.library.view.user.UserDashboard;

import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private UserDAO userDAO = new UserDAO();

    public LoginFrame() {
        setTitle("Library Management - Login");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("LOGIN", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        form.add(new JLabel("Username:"));
        form.add(txtUsername);
        form.add(new JLabel("Password:"));
        form.add(txtPassword);

        add(form, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnLogin = new JButton("Login");
        JButton btnExit = new JButton("Exit");

        btnPanel.add(btnLogin);
        btnPanel.add(btnExit);
        add(btnPanel, BorderLayout.SOUTH);

        // ========= EVENTS =========
        btnLogin.addActionListener(e -> login());
        btnExit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        User user = userDAO.login(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");

            if (((String) user.getRole()).equalsIgnoreCase("admin")) {
                new AdminDashboard();
            } else {
                new UserDashboard(user); // nếu bạn làm giao diện User
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}

