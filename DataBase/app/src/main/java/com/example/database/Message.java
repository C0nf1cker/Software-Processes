package com.example.database;

public class Message {
    private int id;
    private String text,tittle;

    public Message(int id, String text, String tittle) {
        this.id = id;
        this.text = text;
        this.tittle = tittle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
