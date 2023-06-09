package com.calebjones.entities;

public class User extends Person {
    private int userId;

    public User() {
        
    }

    public User(String name, String email, String password, String address) {
        super(name, email, password, address);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
