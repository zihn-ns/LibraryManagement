package com.library.view.admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class HistoryPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    public HistoryPanel() {
        setLayout(new BorderLayout());
        
        // Tạo cột giống TableView bên JavaFX
        String[] columns = {"Mã Mượn", "Tên SV", "Tên Sách", "Ngày Mượn", "Ngày Trả", "Trạng Thái"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        loadHistoryData();
    }
    private void loadHistoryData() {
        // Sau này gọi LoanService.getAllHistory() để lấp dữ liệu vào đây
        // model.addRow(new Object[]{1, "Nguyen Van A", "Java Core", "01/01/2026", "15/01/2026", "Đã Trả"});
    }
}