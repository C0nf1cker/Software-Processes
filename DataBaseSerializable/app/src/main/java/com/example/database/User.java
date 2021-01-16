package com.example.database;

import java.io.Serializable;

public class User implements Serializable {
    private String name, surname, email, password;
    private int score;

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.score = 0;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
