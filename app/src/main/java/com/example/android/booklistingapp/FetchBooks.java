package com.example.android.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

public class FetchBooks extends AsyncTaskLoader<List<Book>> {

    String queryString;

    public FetchBooks(Context context, String queryString) {
        super(context);
        this.queryString = queryString;
    }

    @Override
    public List<Book> loadInBackground() {
        if (queryString != null) {
            return NetworkUtils.fetchBookData(queryString);
        }
        return null;
    }
}
