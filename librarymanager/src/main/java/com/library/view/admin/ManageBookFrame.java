package com.library.view.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.library.dao.BookDAO;
import com.library.model.Book;

import java.awt.*;
import java.util.List;

public class ManageBookFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtId, txtTitle, txtAuthor, txtYear, txtSearch;

    private BookDAO bookDAO = new BookDAO();

    public ManageBookFrame() {
        setTitle("Manage Books");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= TOP =================
        JPanel topPanel = new JPanel(new BorderLayout());

        txtSearch = new JTextField();
        JButton btnSearch = new JButton("Search");

        topPanel.add(new JLabel(" Search: "), BorderLayout.WEST);
        topPanel.add(txtSearch, BorderLayout.CENTER);
        topPanel.add(btnSearch, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ================= TABLE =================
        model = new DefaultTableModel(
                new String[]{"ID", "Title", "Author", "Year"}, 0);

        table = new JTable(model);
        loadBooks();

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ================= FORM =================
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Book Information"));

        txtId = new JTextField();
        txtId.setEnabled(false);
        txtTitle = new JTextField();
        txtAuthor = new JTextField();
        txtYear = new JTextField();

        form.add(new JLabel("ID:"));
        form.add(txtId);
        form.add(new JLabel("Title:"));
        form.add(txtTitle);
        form.add(new JLabel("Author:"));
        form.add(txtAuthor);
        form.add(new JLabel("Year:"));
        form.add(txtYear);

        // ================= BUTTONS =================
        JPanel btnPanel = new JPanel();

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnBack);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(form, BorderLayout.CENTER);
        southPanel.add(btnPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        // ================= EVENTS =================

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtId.setText(model.getValueAt(row, 0).toString());
                txtTitle.setText(model.getValueAt(row, 1).toString());
                txtAuthor.setText(model.getValueAt(row, 2).toString());
                txtYear.setText(model.getValueAt(row, 3).toString());
            }
        });

        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
        btnSearch.addActionListener(e -> searchBook());
        btnBack.addActionListener(e -> {
            new AdminDashboard();
            dispose();
        });

        setVisible(true);
    }

    // ================= FUNCTIONS =================

    private void loadBooks() {
        model.setRowCount(0);
        List<Book> list = bookDAO.getAllBooks();
        for (Book b : list) {
            model.addRow(new Object[]{
                    b.getId(), b.getTitle(), b.getAuthor(), b.getYear()
            });
        }
    }

    private void addBook() {
        Book book = new Book(
                txtTitle.getText(),
                txtAuthor.getText(),
                Integer.parseInt(txtYear.getText())
        );

        if (bookDAO.addBook(book)) {
            JOptionPane.showMessageDialog(this, "Added successfully");
            loadBooks();
        }
    }

    private void updateBook() {
        Book book = new Book(
                Integer.parseInt(txtId.getText()),
                txtTitle.getText(),
                txtAuthor.getText(),
                Integer.parseInt(txtYear.getText())
        );

        if (bookDAO.updateBook(book)) {
            JOptionPane.showMessageDialog(this, "Updated successfully");
            loadBooks();
        }
    }

    private void deleteBook() {
        int id = Integer.parseInt(txtId.getText());
        if (bookDAO.deleteBook(id)) {
            JOptionPane.showMessageDialog(this, "Deleted successfully");
            loadBooks();
        }
    }

    private void searchBook() {
        model.setRowCount(0);
        List<Book> list = bookDAO.searchBooks(txtSearch.getText());
        for (Book b : list) {
            model.addRow(new Object[]{
                    b.getId(), b.getTitle(), b.getAuthor(), b.getYear()
            });
        }
    }
}

