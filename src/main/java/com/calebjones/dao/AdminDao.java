package com.calebjones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.calebjones.database.DBConnectionManager;
import com.calebjones.models.Admin;

public class AdminDao {

    public static void createAdmin(Admin admin) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "INSERT INTO Admin (name, email, password, address) VALUES (?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, admin.getName());
                statement.setString(2, admin.getEmail());
                statement.setString(3, admin.getPassword());
                statement.setString(4, admin.getAddress());

                statement.executeUpdate();

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int adminId = generatedKeys.getInt(1);
                        admin.setAdminId(adminId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } // End Method: createAdmin

    public static Admin getAdminById(int adminId) {
        Admin admin = null;

        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "SELECT * FROM Admin WHERE admin_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, adminId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        admin = new Admin();
                        admin.setAdminId(resultSet.getInt("admin_id"));
                        admin.setName(resultSet.getString("name"));
                        admin.setEmail(resultSet.getString("email"));
                        admin.setPassword(resultSet.getString("password"));
                        admin.setAddress(resultSet.getString("address"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;

    } // End getAdminById

    public static void updateAdmin(Admin admin) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "UPDATE Admin SET name = ?, email = ?, password = ?, address = ? WHERE admin_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, admin.getName());
                statement.setString(2, admin.getEmail());
                statement.setString(3, admin.getPassword());
                statement.setString(4, admin.getAddress());
                statement.setInt(5, admin.getAdminId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } // End updateAdmin

    public static void deleteAdmin(int adminId) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "DELETE FROM Admin WHERE admin_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, adminId);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // End deleteAdmin

    public static List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();

        try (Connection connection = DBConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Admin");
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminId(resultSet.getInt("admin_id"));
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setAddress(resultSet.getString("address"));

                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    } // End getAllAdmins

} // End Class AdminDao