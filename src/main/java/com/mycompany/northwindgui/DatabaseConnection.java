/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.northwindgui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author leroy
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mariadb://localhost:3307/u22496336_u23545527_northwind";
    private static final String USER = "root";
    private static final String PASSWORD = "KC@!12970thebe";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    

}
