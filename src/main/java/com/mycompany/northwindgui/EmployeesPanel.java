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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.mycompany.northwindgui.data.EmployeeDAO;

public class EmployeesPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchButton;

    public EmployeesPanel() {
        setLayout(new BorderLayout());

        // Create search panel
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search by Name or Job Title:"));
        searchField = new JTextField(15);
        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> filterEmployees());
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Create table model
        tableModel = new DefaultTableModel(new String[]{"ID", "First Name", "Last Name", "Job Title"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadEmployees();
    }

    private void loadEmployees() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<String[]> employees = employeeDAO.getAllEmployees();
        tableModel.setRowCount(0);
        for (String[] emp : employees) {
            tableModel.addRow(emp);
        }
    }

    private void filterEmployees() {
        String keyword = searchField.getText().trim();
        if (!keyword.isEmpty()) {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<String[]> filteredEmployees = employeeDAO.getFilteredEmployees(keyword);
            tableModel.setRowCount(0);
            for (String[] emp : filteredEmployees) {
                tableModel.addRow(emp);
            }
        } else {
            loadEmployees();  // If no filter, reload all employees
        }
    }
}
