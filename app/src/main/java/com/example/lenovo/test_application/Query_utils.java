package com.example.lenovo.test_application;

import android.text.TextUtils;
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

public class Query_utils {
    public static final String LOG_TAG = Query_utils.class.getSimpleName();


    public Query_utils() {
    }
    public static List<books> fetchBooksData(String stringUrl){
        URL url=createUrl(stringUrl);
        String jsonResponse="";
        jsonResponse=httpRequest(url);
        List<books> BookList=extractDetails(jsonResponse);
        return  BookList;
    }
    private static URL createUrl(String urls){
        URL string=null;
        try {
            string=new URL(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return string;
    }

    private static String httpRequest(URL url) {
        String jsonResponse="";
        if (url==null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
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
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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


    public static List<books> extractDetails(String bookJSON) {
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }
        List<books> booksArrayList = new ArrayList<>();
        try {
            JSONObject baseJson = new JSONObject(bookJSON);
            JSONArray jsonArray = baseJson.getJSONArray("book_array");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("book_title");
                String image = jsonObject.getString("image");
                String author = jsonObject.getString("author");
                books books = new books(title, author, image);
                booksArrayList.add(books);
            }
        } catch (JSONException e) {
            Log.e("Query", "Problem with JSON");
        }
        return booksArrayList;
    }
}
