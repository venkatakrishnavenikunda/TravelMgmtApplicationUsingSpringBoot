package com.example.demo.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



@Component
public class DatabaseInitializer {

    private static final String DB_NAME = "timetotraveldb";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    @PostConstruct
    public void createDatabaseIfNotExists() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Check if database already exists
            ResultSet resultSet = statement.executeQuery(
                    "SELECT 1 FROM pg_database WHERE datname = '" + DB_NAME + "'"
            );

            if (!resultSet.next()) {
                // Database does not exist → create it
                statement.executeUpdate("CREATE DATABASE " + DB_NAME);
                System.out.println("Database created: " + DB_NAME);
            } else {
                System.out.println("Database already exists: " + DB_NAME);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
