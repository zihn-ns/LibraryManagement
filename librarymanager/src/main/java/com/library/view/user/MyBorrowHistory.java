package com.library.view.user;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class MyBorrowHistory extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    public MyBorrowHistory() {
        setLayout(new BorderLayout());

        // Tạo bảng đơn giản
        String[] cols = {"Mã Sách", "Ngày Mượn", "Ngày Trả", "Trạng Thái"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Giả lập dữ liệu (Sau này gọi Service lấy thật)
        model.addRow(new Object[]{"101", "01/01/2026", "15/01/2026", "Đang mượn"});
    }
}
