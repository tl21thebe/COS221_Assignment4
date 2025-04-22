/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.northwindgui.data;

/**
 *
 * @author leroy
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import com.mycompany.northwindgui.DatabaseConnection;

public class ReportDAO {
    public List<String[]> getReport(String reportType) {
        List<String[]> reportData = new ArrayList<>();
        String sql = "";

        switch (reportType) {
            case "Sales By Product":
                sql = "SELECT product_name, SUM(quantity) AS total_sold FROM order_details " +
                      "JOIN products ON order_details.product_id = products.id " +
                      "GROUP BY product_name ORDER BY total_sold DESC";
                break;
            case "Sales By Category":
                sql = "SELECT category, SUM(quantity) AS total_sold FROM order_details " +
                      "JOIN products ON order_details.product_id = products.id " +
                      "GROUP BY category ORDER BY total_sold DESC";
                break;
            case "Inventory Transactions":
                sql = "SELECT transaction_type, product_id, quantity FROM inventory_transactions";
                break;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                reportData.add(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportData;
    }
}
