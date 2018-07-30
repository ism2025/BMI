package com.example.ismailamassi.bmi.models;

public class User {
    private String id;
    private String username;
    private String email;
    private String age;
    private int role;
    private String token;

    public User(String id, String username, String email, String age, int role, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
        this.role = role;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public int getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}
