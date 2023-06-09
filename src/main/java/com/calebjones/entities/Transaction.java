package com.calebjones.entities;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private double amount;
    private Date date;
    private String type;
    private int accountId;

    public Transaction() {
    }

    public Transaction(double amount, Date date, String type, int accountId) {
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.accountId = accountId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
