package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to connect to database - " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to close database connection - " + e.getMessage());
        }
    }
}
