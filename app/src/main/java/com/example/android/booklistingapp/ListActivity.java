package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class ListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private String queryString;
    private static final int LOADER_ID = 0;
    private ListView listView;
    private TextView noData;
    private final static String LIST_KEY = "list key";
    private Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        listView = findViewById(R.id.list);
        noData = findViewById(R.id.no_data);

        Bundle extras = getIntent().getExtras();
        queryString = extras.getString(MainActivity.QUERY_KEY);

        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_KEY);
        }

        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();

        Log.d("", "");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        listState = listView.onSaveInstanceState();
        outState.putParcelable(LIST_KEY, listState);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {

        return new FetchBooks(this, queryString);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        if (books == null || books.size() == 0) {
            noData.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            noData.setVisibility(View.GONE);
            BookListAdapter adapter = new BookListAdapter(this, books);
            listView.setAdapter(adapter);
            if (listState != null) {
                listView.onRestoreInstanceState(listState);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
    }
}
