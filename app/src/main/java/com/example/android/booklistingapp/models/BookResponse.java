package com.example.android.booklistingapp.models;

import java.util.List;

public class BookResponse {
   private List<BookResponseItem> items;

    public List<BookResponseItem> getItems() {
        return items;
    }

    public void setItems(List<BookResponseItem> items) {
        this.items = items;
    }
}
