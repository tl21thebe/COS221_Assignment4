/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.northwindgui;

/**
 *
 * @author leroy
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;

import com.mycompany.northwindgui.data.ReportDAO;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ReportsPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> reportTypeDropdown;
    private JButton generateReportButton;

    public ReportsPanel() {
        setLayout(new BorderLayout());
        

        // Create dropdown to select report type
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select Report Type:"));
        reportTypeDropdown = new JComboBox<>(new String[]{"Sales By Product", "Sales By Category", "Inventory Transactions"});
        generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(e -> generateReport());
        controlPanel.add(reportTypeDropdown);
        controlPanel.add(generateReportButton);
        add(controlPanel, BorderLayout.NORTH);

        // Table model setup
        tableModel = new DefaultTableModel(new String[]{"Column 1", "Column 2", "Column 3"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    

    private void generateReport() {
        String selectedReport = (String) reportTypeDropdown.getSelectedItem();
        ReportDAO reportDAO = new ReportDAO();
        List<String[]> reportData = reportDAO.getReport(selectedReport);

        tableModel.setRowCount(0); // Clear previous data
        for (String[] row : reportData) {
            tableModel.addRow(row);
        }
    }
}
