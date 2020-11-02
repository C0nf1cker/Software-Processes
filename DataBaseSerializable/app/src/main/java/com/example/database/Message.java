package com.example.database;

import java.io.Serializable;

public class Message implements Serializable {
    private int id;
    private String description;

    public Message(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
