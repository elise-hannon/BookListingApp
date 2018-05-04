package com.example.android.booklistingapp;

import android.util.Log;
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
        List<Book> books = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(googleBooksJSON);
            JSONArray bookArray = baseJsonResponse.getJSONArray("items");

            for (int i = 0; i < bookArray.length(); i++) {
                JSONObject currentBook = bookArray.getJSONObject(i);
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");
                JSONObject volumeImageLinks = volumeInfo.optJSONObject("imageLinks");

                String title = volumeInfo.optString("title");
                JSONArray authors = volumeInfo.optJSONArray("authors");
                List<String> authorList = new ArrayList<>();

                if (authors != null) {
                    for (int j = 0; j < authors.length(); j++) {
                        authorList.add(authors.optString(j));
                    }
                } else {
                    authorList.add("unknown");
                }
                String description = volumeInfo.optString("description");
                String imageUrl = null;
                if (volumeImageLinks != null) {
                    imageUrl = volumeImageLinks.optString("smallThumbnail");
                }
                books.add(new Book(title, authorList, description, imageUrl));
            }

        } catch (JSONException e) {
            Log.e("NetworkUtils", "Problem parsing the JSON results", e);
        }
        return books;
    }
}
