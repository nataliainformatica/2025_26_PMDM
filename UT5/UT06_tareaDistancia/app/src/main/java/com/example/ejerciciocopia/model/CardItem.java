package com.example.ejerciciocopia.model;

public class CardItem {
    private int imageRes;
    private String title;
    private String description;

    public CardItem(int imageRes, String title, String description) {
        this.imageRes = imageRes;
        this.title = title;
        this.description = description;
    }

    public int getImageRes() { return imageRes; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}

