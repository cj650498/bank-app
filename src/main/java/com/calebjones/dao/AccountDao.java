package com.calebjones.dao;

import com.calebjones.database.DBConnectionManager;
import com.calebjones.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    public static void createAccount(Account account) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "INSERT INTO Account (account_number, balance, user_id) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, account.getAccountNumber());
                statement.setDouble(2, account.getBalance());
                statement.setInt(3, account.getUserId());

                statement.executeUpdate();

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int accountId = generatedKeys.getInt(1);
                        account.setAccountId(accountId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } // End createAccount

    public static Account getAccountById(int accountId) {
        Account account = null;

        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "SELECT * FROM Account WHERE account_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, accountId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        account = new Account();
                        account.setAccountId(resultSet.getInt("account_id"));
                        account.setAccountNumber(resultSet.getString("account_number"));
                        account.setBalance(resultSet.getDouble("balance"));
                        account.setUserId(resultSet.getInt("user_id"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;

    } // End getAccountById

    public static List<Account> getAccountsByUserId(int userId) {
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "SELECT * FROM Account WHERE user_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Account account = new Account();
                        account.setAccountId(resultSet.getInt("account_id"));
                        account.setAccountNumber(resultSet.getString("account_number"));
                        account.setBalance(resultSet.getDouble("balance"));
                        account.setUserId(resultSet.getInt("user_id"));

                        accounts.add(account);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;

    } // End getAccountsByUserId

    public static void updateAccount(Account account) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "UPDATE Account SET account_number = ?, balance = ?, user_id = ? WHERE account_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, account.getAccountNumber());
                statement.setDouble(2, account.getBalance());
                statement.setInt(3, account.getUserId());
                statement.setInt(4, account.getAccountId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } // End updateAccount

    public static void deleteAccount(int accountId) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "DELETE FROM Account WHERE account_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, accountId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } // End deleteAccount

    public static List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "SELECT * FROM Account";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Account account = new Account();
                        account.setAccountId(resultSet.getInt("account_id"));
                        account.setAccountNumber(resultSet.getString("account_number"));
                        account.setBalance(resultSet.getDouble("balance"));
                        account.setUserId(resultSet.getInt("user_id"));

                        accounts.add(account);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;

    } // End getAllAccounts
    
} // End Class AccountDao
