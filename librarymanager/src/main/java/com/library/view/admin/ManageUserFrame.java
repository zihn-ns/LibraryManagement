package com.library.view.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.library.dao.UserDAO;
import com.library.model.User;

import java.awt.*;
import java.util.List;

public class ManageUserFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtId, txtUsername, txtPassword;
    private JComboBox<String> cboRole;

    private UserDAO userDAO = new UserDAO();

    public ManageUserFrame() {
        setTitle("Manage Users");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"ID", "Username", "Role"}, 0);

        table = new JTable(model);
        loadUsers();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== FORM =====
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("User Information"));

        txtId = new JTextField();
        txtId.setEnabled(false);
        txtUsername = new JTextField();
        txtPassword = new JTextField();

        cboRole = new JComboBox<>(new String[]{"admin", "user"});

        form.add(new JLabel("ID:"));
        form.add(txtId);
        form.add(new JLabel("Username:"));
        form.add(txtUsername);
        form.add(new JLabel("Password:"));
        form.add(txtPassword);
        form.add(new JLabel("Role:"));
        form.add(cboRole);

        // ===== BUTTONS =====
        JPanel btnPanel = new JPanel();

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnBack);

        JPanel south = new JPanel(new BorderLayout());
        south.add(form, BorderLayout.CENTER);
        south.add(btnPanel, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);

        // ===== EVENTS =====
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtId.setText(model.getValueAt(row, 0).toString());
                txtUsername.setText(model.getValueAt(row, 1).toString());
                cboRole.setSelectedItem(model.getValueAt(row, 2).toString());
            }
        });

        btnAdd.addActionListener(e -> addUser());
        btnUpdate.addActionListener(e -> updateUser());
        btnDelete.addActionListener(e -> deleteUser());

        btnBack.addActionListener(e -> {
            new AdminDashboard();
            dispose();
        });

        setVisible(true);
    }

    // ===== FUNCTIONS =====
    private void loadUsers() {
        model.setRowCount(0);
        List<User> list = userDAO.getAllUsers();
        for (User u : list) {
            model.addRow(new Object[]{
                    u.getId(), u.getUsername(), u.getRole()
            });
        }
    }

    private void addUser() {
        User u = new User(
                txtUsername.getText(),
                txtPassword.getText(),
                cboRole.getSelectedItem().toString()
        );

        if (userDAO.addUser(u)) {
            JOptionPane.showMessageDialog(this, "User added successfully");
            loadUsers();
        }
    }

    private void updateUser() {
        User u = new User(
                Integer.parseInt(txtId.getText()),
                txtUsername.getText(),
                txtPassword.getText(),
                cboRole.getSelectedItem().toString()
        );

        if (userDAO.updateUser(u)) {
            JOptionPane.showMessageDialog(this, "User updated successfully");
            loadUsers();
        }
    }

    private void deleteUser() {
        int id = Integer.parseInt(txtId.getText());
        if (userDAO.deleteUser(id)) {
            JOptionPane.showMessageDialog(this, "User deleted");
            loadUsers();
        }
    }
}

