/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microreserva.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Laura
 */
public class DatabaseConnection {
    
    private static final String URL = "jdbc:postgresql://localhost:5433/parkingdb";
    private static final String USER  = "postgres";
    private static final String PASSWORD  = "admin";
    
    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected to Database.");
        return connection;
    }
}
