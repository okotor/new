package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLExample {
    public static void main(String[] args) {
        // PostgreSQL connection URL and credentials
        String url = "jdbc:postgresql://yamabiko.proxy.rlwy.net:46958/railway";
        String user = "postgres";
        String password = "rcDZJZbbBpuTFYrhSJUGKGvmpPMCYTkZ";

        // Try-with-resources statement to ensure the connection is closed automatically
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Check if the connection is successful
            if (conn != null) {
                System.out.println("Connected to the database!");

                // Create a statement object to execute SQL queries
                try (Statement stmt = conn.createStatement()) {

                    // Create a table for logging if it doesn't already exist
                    String createTableSQL = "CREATE TABLE IF NOT EXISTS connection_logs (" +
                            "id SERIAL PRIMARY KEY, " +
                            "log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
                    stmt.execute(createTableSQL);

                    // Insert a log entry into the table
                    String insertLogSQL = "INSERT INTO connection_logs (log_time) VALUES (CURRENT_TIMESTAMP)";
                    stmt.execute(insertLogSQL);

                    System.out.println("Log entry inserted into connection_logs table.");

                    // Create another table
                    String createAnotherTableSQL = "CREATE TABLE IF NOT EXISTS another_table (" +
                            "id SERIAL PRIMARY KEY, " +
                            "description TEXT NOT NULL)";
                    stmt.execute(createAnotherTableSQL);

                    System.out.println("Table another_table created successfully.");
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            // Print the stack trace for any exception that occurs
            e.printStackTrace();
        }
    }
}