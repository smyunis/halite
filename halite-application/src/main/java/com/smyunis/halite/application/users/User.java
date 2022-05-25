package com.smyunis.halite.application.users;

import com.smyunis.halite.domain.shared.PhoneNumber;

public class User {
    private PhoneNumber phoneNumber;
    private String password;
    private UserRole role;

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }


    public UserRole getRole() {
        return role;
    }

    public User setRole(UserRole role) {
        this.role = role;
        return this;
    }
}
