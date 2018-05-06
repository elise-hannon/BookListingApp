package com.example.android.booklistingapp;

import android.util.Log;
import com.example.android.booklistingapp.models.BookResponse;
import com.example.android.booklistingapp.models.BookResponseItem;
import com.example.android.booklistingapp.models.ImageLinks;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class NetworkUtils {

    private static final String URL_TEMPLATE = "https://www.googleapis.com/books/v1/volumes?q=%s&maxResults=%d";
    private static final int MAX_RESULTS = 20;
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();


    static List<Book> fetchBookData(String queryString) {
        URL url = createUrl(String.format(Locale.getDefault(), URL_TEMPLATE, queryString, MAX_RESULTS));

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        return extractVolumefromJson(jsonResponse);
    }

    private static URL createUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    static List<Book> extractVolumefromJson(String googleBooksJSON) {
        if (googleBooksJSON == null || googleBooksJSON.equals("")) {
            return null;
        }
        Gson gson = new Gson();
        BookResponse bookResponse = gson.fromJson(googleBooksJSON, BookResponse.class);
        List<Book> books = new ArrayList<>();

        for (BookResponseItem item : bookResponse.getItems()) {
            List<String> authorList = item.getVolumeInfo().getAuthors();

            if (authorList == null) {
                authorList = new ArrayList<String>();
                authorList.add("unknown");
            }
            String imageUrl = null;
            if (item.getVolumeInfo().getImageLinks() != null) {
                imageUrl = item.getVolumeInfo().getImageLinks().getSmallThumbnail();
            }
            books.add(new Book(item.getVolumeInfo().getTitle(), authorList, item.getVolumeInfo().getDescription(),
                               imageUrl));
        }

        return books;
    }
}
