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

public class NotificationDAO {
    public List<String> getAllNotifications() {
        List<String> notifications = new ArrayList<>();
        String sql = "SELECT string_data FROM strings WHERE string_id BETWEEN 2 AND 50";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                notifications.add(rs.getString("string_data"));
            }
        } catch (SQLException e) {
            notifications.add("Error retrieving notifications!");
            e.printStackTrace();
        }
        return notifications;
    }
}
