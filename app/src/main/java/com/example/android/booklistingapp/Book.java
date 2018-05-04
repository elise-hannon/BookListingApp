package com.example.android.booklistingapp;

import java.util.List;

public class Book {
    private String title;
    private List<String> authors;
    private String description;
    private String imageUrl;

    public Book(String title, List<String> authors, String description, String imageUrl) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean hasImage() {
        return imageUrl != null;
    }
}
