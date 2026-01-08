package com.library.view.user;
import com.library.service.LoanService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
public class BorrowPanel extends JPanel {
    private JTextField txtStudentId = new JTextField(20);
    private JTextField txtStudentName = new JTextField(20);
    private JTextField txtBookId = new JTextField(20);
    private JTextField txtBorrowDate = new JTextField(20);
    private JTextField txtReturnDate = new JTextField(20);
    private JButton btnBorrow = new JButton("MƯỢN SÁCH");
    private LoanService loanService = new LoanService();
    public BorrowPanel() {
        initComponents();
    }
    private void initComponents() {
        setLayout(new GridLayout(6, 2, 10, 10));
        add(new JLabel("Mã Sinh Viên:")); add(txtStudentId);
        add(new JLabel("Tên Sinh Viên:"));add(txtStudentName);
        add(new JLabel("Mã Sách:"));      add(txtBookId);
        add(new JLabel("Ngày Mượn (dd/MM/yyyy):"));
        txtBorrowDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        add(txtBorrowDate);
        add(new JLabel("Ngày Trả (dd/MM/yyyy):"));
        add(txtReturnDate);

        add(new JLabel(""));
        add(btnBorrow);
        btnBorrow.addActionListener(e -> performBorrow());
    }
    private void performBorrow() {
        try {
            int bookId = Integer.parseInt(txtBookId.getText());
            int sId = Integer.parseInt(txtStudentId.getText());

            // Gọi Service mượn sách
            boolean success = loanService.borrowBook(sId, bookId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Mượn sách thành công!");
                txtBookId.setText(""); // Reset form
            } else {
                JOptionPane.showMessageDialog(this, "Mượn thất bại (Hết sách hoặc lỗi)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập liệu: " + ex.getMessage());
        }
    }
}