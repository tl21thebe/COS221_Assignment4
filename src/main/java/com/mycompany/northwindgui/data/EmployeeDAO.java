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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.northwindgui.DatabaseConnection;

public class EmployeeDAO {
    public List<String[]> getAllEmployees() {
        List<String[]> employees = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, job_title FROM employees";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                employees.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("job_title")
                });
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    public List<String[]> getFilteredEmployees(String keyword) {
    List<String[]> employees = new ArrayList<>();
    String sql = "SELECT id, first_name, last_name, job_title FROM employees WHERE first_name LIKE ? OR job_title LIKE ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + keyword + "%");
        stmt.setString(2, "%" + keyword + "%");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            employees.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("job_title")
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return employees;
}
}
