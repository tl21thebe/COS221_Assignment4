/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.northwindgui.data;

/**
 *
 * @author leroy
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import com.mycompany.northwindgui.DatabaseConnection;


public class ProductDAO {

    // Fetch all products from the database
    public List<String[]> getAllProducts() {
        List<String[]> products = new ArrayList<>();
        String sql = "SELECT id, product_name, list_price, category FROM products";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("product_name"),
                        rs.getBigDecimal("list_price").toString(),
                        rs.getString("category")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Fetch filtered products by keyword
    public List<String[]> getFilteredProducts(String keyword) {
        List<String[]> products = new ArrayList<>();
        String sql = "SELECT id, product_name, list_price, category FROM products WHERE product_name LIKE ? OR category LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("product_name"),
                        rs.getBigDecimal("list_price").toString(),
                        rs.getString("category")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Add a new product to the database
    public boolean addProduct(String name, String price, String category) {
        String sql = "INSERT INTO products (product_name, list_price, category) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setBigDecimal(2, new java.math.BigDecimal(price));
            stmt.setString(3, category);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a product from the database
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
