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

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Northwind Traders GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Employees", new EmployeesPanel());
        tabbedPane.addTab("Products", new ProductsPanel());
        tabbedPane.addTab("Reports", new ReportsPanel());
        tabbedPane.addTab("Notifications", new NotificationsPanel());
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
