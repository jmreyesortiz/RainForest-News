package com.juanma.rainforestnews;

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

public class QueryUtils {


    private static final String LOG_TAG = "NewsApp";
    private QueryUtils() {
    }
    private static final String API_KEY ="36ff0e226c7048fdab6d987813d163fb";

    public static List<News> fetchNewsData(String jsonInput){

        //Used for the Progres Bar to show up
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<News> newsList = new ArrayList<>();
        //String APi Key Appending key to URL input


        //Try and Catch for JSON Object creation.
        try{


            String headline = "";
            String date = "1991";
            String imageURL = "";
            String newsSource = "";
            String url = "";



            JSONObject newsObject = new JSONObject(jsonInput);
            JSONArray articles = newsObject.getJSONArray("articles");


            for(int i = 0; i<articles.length(); i++){
                JSONObject article = articles.getJSONObject(i);
                JSONObject source = article.getJSONObject("source");
                newsSource = source.getString("name");

                headline = article.getString("title");
                date = article.getString("publishedAt");
                url = article.getString("url");
                imageURL = article.getString("urlToImage");
                newsList.add(new News(headline,date,imageURL,newsSource,url));

            }


        }catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }


        return newsList;
    }


    public static String httpRequest(String url) throws IOException {

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse = "";
<<<<<<< HEAD
=======
        String API_KEY = "Your Api Key";    //Api key from newsapi.org
>>>>>>> 5b5e87d3d077d1c86f9409d07f6933c65893d3c1
        String urlString = url + API_KEY;


        try{

            URL urlObject = new URL(urlString);
            urlConnection = (HttpURLConnection) urlObject.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

            else
            {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (
    MalformedURLException e) {
        e.printStackTrace();
    } catch (
    IOException e) {
        e.printStackTrace();
    }finally {
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
        if (inputStream != null) {
            // function must handle java.io.IOException here
            inputStream.close();
        }
    }

        return jsonResponse;
    }


    public static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line= reader.readLine();

            while(line != null){
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();

    }

}
