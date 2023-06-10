package com.calebjones.database;

import io.github.cdimascio.dotenv.Dotenv;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {

    private static HikariDataSource dataSource;

    static {
        initializeDataSource();
    }

    private static void initializeDataSource() {
        Dotenv dotenv = Dotenv.load();
        String host = dotenv.get("MYSQL_HOST");
        String port = dotenv.get("MYSQL_PORT");
        String database = dotenv.get("MYSQL_DATABASE");
        String username = dotenv.get("MYSQL_USER");
        String password = dotenv.get("MYSQL_PASSWORD");

        String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database;

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        dataSource = new HikariDataSource(config);
        System.out.println("Connected to the database!");
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the connection.");
                e.printStackTrace();
            }
        }
    }

    // Might only use this for testing
    public static void clearDatabase() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Transaction");
            statement.executeUpdate("ALTER TABLE Transaction AUTO_INCREMENT = 1");
            statement.executeUpdate("DELETE FROM Account");
            statement.executeUpdate("ALTER TABLE Account AUTO_INCREMENT = 1");
            statement.executeUpdate("DELETE FROM Admin");
            statement.executeUpdate("ALTER TABLE Admin AUTO_INCREMENT = 1");
            statement.executeUpdate("DELETE FROM User");
            statement.executeUpdate("ALTER TABLE User AUTO_INCREMENT = 1");
            System.out.println("Database cleared successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to clear the database.");
            e.printStackTrace();
        }
    }
}
