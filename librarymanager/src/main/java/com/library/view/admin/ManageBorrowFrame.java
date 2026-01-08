package com.library.view.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.library.dao.BorrowerDAO;
import com.library.model.Borrower;

import java.awt.*;
import java.util.List;

public class ManageBorrowFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtBorrowId, txtUserId, txtBookId, txtDate;
    private BorrowerDAO borrowDAO = new BorrowerDAO();

    public ManageBorrowFrame() {
        setTitle("Manage Borrow / Return");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"Borrow ID", "User ID", "Book ID", "Borrow Date", "Status"}, 0);

        table = new JTable(model);
        loadBorrows();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== FORM =====
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Borrow Information"));

        txtBorrowId = new JTextField();
        txtBorrowId.setEnabled(false);
        txtUserId = new JTextField();
        txtBookId = new JTextField();
        txtDate = new JTextField();

        form.add(new JLabel("Borrow ID:"));
        form.add(txtBorrowId);
        form.add(new JLabel("User ID:"));
        form.add(txtUserId);
        form.add(new JLabel("Book ID:"));
        form.add(txtBookId);
        form.add(new JLabel("Borrow Date:"));
        form.add(txtDate);

        // ===== BUTTONS =====
        JPanel btnPanel = new JPanel();

        JButton btnBorrow = new JButton("Borrow");
        JButton btnReturn = new JButton("Return Book");
        JButton btnBack = new JButton("Back");

        btnPanel.add(btnBorrow);
        btnPanel.add(btnReturn);
        btnPanel.add(btnBack);

        JPanel south = new JPanel(new BorderLayout());
        south.add(form, BorderLayout.CENTER);
        south.add(btnPanel, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);

        // ===== EVENTS =====
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtBorrowId.setText(model.getValueAt(row, 0).toString());
                txtUserId.setText(model.getValueAt(row, 1).toString());
                txtBookId.setText(model.getValueAt(row, 2).toString());
                txtDate.setText(model.getValueAt(row, 3).toString());
            }
        });

        btnBorrow.addActionListener(e -> borrowBook());
        btnReturn.addActionListener(e -> returnBook());

        btnBack.addActionListener(e -> {
            new AdminDashboard();
            dispose();
        });

        setVisible(true);
    }

    // ===== FUNCTIONS =====
    private void loadBorrows() {
        model.setRowCount(0);
        List<Borrower> list = borrowDAO.getAllBorrows();
        for (Borrower b : list) {
            model.addRow(new Object[]{
                    b.getId(), b.getUserId(), b.getBookId(),
                    b.getBorrowDate(), b.getStatus()
            });
        }
    }

    private void borrowBook() {
        Borrower b = new Borrower(
                Integer.parseInt(txtUserId.getText()),
                Integer.parseInt(txtBookId.getText()),
                txtDate.getText()
        );

        if (borrowDAO.borrowBook(b)) {
            JOptionPane.showMessageDialog(this, "Book borrowed successfully");
            loadBorrows();
        }
    }

    private void returnBook() {
        int borrowId = Integer.parseInt(txtBorrowId.getText());
        if (borrowDAO.returnBook(borrowId)) {
            JOptionPane.showMessageDialog(this, "Book returned successfully");
            loadBorrows();
        }
    }
}

