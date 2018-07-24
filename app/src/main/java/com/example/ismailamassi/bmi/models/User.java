package com.example.ismailamassi.bmi.models;

public class User {
    private int id;
    private String username;
    private String email;
    private int role;
    private String token;

    public User(int id, String username, String email, int role, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}
