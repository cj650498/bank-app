package com.calebjones.entities;

public class Admin extends Person {
    private int adminId;

    public Admin() {
        
    }

    public Admin(String name, String email, String password, String address) {
        super(name, email, password, address);
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
