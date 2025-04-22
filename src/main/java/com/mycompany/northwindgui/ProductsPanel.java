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
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mycompany.northwindgui.data.ProductDAO;


public class ProductsPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField, priceField, categoryField, searchField;
    private JButton addButton, removeButton, searchButton;

    public ProductsPanel() {
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search by Name or Category:"));
        searchField = new JTextField(15);
        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> filterProducts());
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Table Model
        tableModel = new DefaultTableModel(new String[]{"ID", "Product Name", "List Price", "Category"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Action Panel
        JPanel actionPanel = new JPanel();
        nameField = new JTextField(10);
        priceField = new JTextField(5);
        categoryField = new JTextField(10);
        addButton = new JButton("Add Product");
        removeButton = new JButton("Remove Selected");
        addButton.addActionListener(e -> addProduct());
        removeButton.addActionListener(e -> removeSelectedProduct());

        actionPanel.add(new JLabel("Name:")); actionPanel.add(nameField);
        actionPanel.add(new JLabel("Price:")); actionPanel.add(priceField);
        actionPanel.add(new JLabel("Category:")); actionPanel.add(categoryField);
        actionPanel.add(addButton);
        actionPanel.add(removeButton);

        add(actionPanel, BorderLayout.SOUTH);

        loadProducts();
    }

    private void loadProducts() {
        ProductDAO productDAO = new ProductDAO();
        List<String[]> products = productDAO.getAllProducts();
        tableModel.setRowCount(0);
        for (String[] prod : products) {
            tableModel.addRow(prod);
        }
    }

    private void filterProducts() {
        String keyword = searchField.getText().trim();
        if (!keyword.isEmpty()) {
            ProductDAO productDAO = new ProductDAO();
            List<String[]> filteredProducts = productDAO.getFilteredProducts(keyword);
            tableModel.setRowCount(0);
            for (String[] prod : filteredProducts) {
                tableModel.addRow(prod);
            }
        } else {
            loadProducts();
        }
    }

    private void addProduct() {
        ProductDAO productDAO = new ProductDAO();
        boolean success = productDAO.addProduct(nameField.getText(), priceField.getText(), categoryField.getText());

        if (success) {
            JOptionPane.showMessageDialog(this, "Product added successfully!");
            loadProducts(); // Refresh table
        } else {
            JOptionPane.showMessageDialog(this, "Error adding product.");
        }
    }

    private void removeSelectedProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String productId = (String) tableModel.getValueAt(selectedRow, 0);
            ProductDAO productDAO = new ProductDAO();
            boolean success = productDAO.deleteProduct(Integer.parseInt(productId));

            if (success) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                loadProducts();
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting product.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a product to delete.");
        }
    }
}
