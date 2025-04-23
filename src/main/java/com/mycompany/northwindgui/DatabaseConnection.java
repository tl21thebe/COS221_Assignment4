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
    
    public static Connection getConnection() throws SQLException {
        String protocol = System.getenv("dvdrental_DB_PROTO");     
        String host = System.getenv("dvdrental_DB_HOST");          
        String port = System.getenv("dvdrental_DB_PORT");          
        String dbName = System.getenv("dvdrental_DB_NAME");        
        String username = System.getenv("dvdrental_DB_USERNAME");  
        String password = System.getenv("dvdrental_DB_PASSWORD");
        


        String url = String.format("%s://%s:%s/%s", protocol, host, port, dbName);

        return DriverManager.getConnection(url, username, password);
    }
}
