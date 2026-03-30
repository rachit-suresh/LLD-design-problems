package com.bookmyshow;

public class User {
    private UserType type;
    private String username;

    public User(UserType type, String username) {
        this.type = type;
        this.username = username;
    }

    public boolean login() {
        return true;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
