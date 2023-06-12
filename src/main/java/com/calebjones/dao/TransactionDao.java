package com.calebjones.dao;

import com.calebjones.database.DBConnectionManager;
import com.calebjones.entities.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    public static void createTransaction(Transaction transaction) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "INSERT INTO Transaction (amount, date, type, account_id) VALUES (?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setDouble(1, transaction.getAmount());
                statement.setDate(2, new java.sql.Date(transaction.getDate().getTime()));
                statement.setString(3, transaction.getType());
                statement.setInt(4, transaction.getAccountId());

                statement.executeUpdate();

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int transactionId = generatedKeys.getInt(1);
                        transaction.setTransactionId(transactionId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "SELECT * FROM Transaction WHERE account_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, accountId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Transaction transaction = new Transaction();
                        transaction.setTransactionId(resultSet.getInt("transaction_id"));
                        transaction.setAmount(resultSet.getDouble("amount"));
                        transaction.setDate(resultSet.getDate("date"));
                        transaction.setType(resultSet.getString("type"));
                        transaction.setAccountId(resultSet.getInt("account_id"));

                        transactions.add(transaction);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public static void deleteTransaction(int transactionId) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "DELETE FROM Transaction WHERE transaction_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, transactionId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "SELECT * FROM Transaction";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Transaction transaction = new Transaction();
                        transaction.setTransactionId(resultSet.getInt("transaction_id"));
                        transaction.setAmount(resultSet.getDouble("amount"));
                        transaction.setDate(resultSet.getDate("date"));
                        transaction.setType(resultSet.getString("type"));
                        transaction.setAccountId(resultSet.getInt("account_id"));

                        transactions.add(transaction);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
