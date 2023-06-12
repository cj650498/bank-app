package com.calebjones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.calebjones.database.DBConnectionManager;
import com.calebjones.entities.User;

public class UserDao {
    public static void createUser(User user) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "INSERT INTO User (name, email, password, address) VALUES (?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                // Set the values for the prepared statement
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getAddress());

                // Execute the query
                statement.executeUpdate();

                // Retrieve the generated keys (including the assigned ID)
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        user.setUserId(userId); // Update the User object with the assigned ID
                    }
                }
            }
        } catch (SQLException e) {
            // Handle any potential exceptions
            e.printStackTrace();
        }
    } // END: createUser

    public static User getUserById(int userId) {
        User user = null;

        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "SELECT * FROM User WHERE user_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User();
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setName(resultSet.getString("name"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        user.setAddress(resultSet.getString("address"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    } // END: getUserById

    public static void updateUser(User user) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "UPDATE User SET name = ?, email = ?, password = ?, address = ? WHERE user_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getAddress());
                statement.setInt(5, user.getUserId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // END: updateUser

    public static void deleteUser(int userId) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "DELETE FROM User WHERE user_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Set the value for the prepared statement
                statement.setInt(1, userId);

                // Execute the delete query
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // END: deleteUser

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DBConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM User");
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAddress(resultSet.getString("address"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    } // END: getAllUsers

    public static int getUserCount() {
        int count = 0;

        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "SELECT COUNT(*) FROM User";

            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    } // END: getUserCount
    
} // END CLASS
