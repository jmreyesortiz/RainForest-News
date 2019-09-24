package com.juanma.rainforestnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private ListView mNewsListView;
    private String URL = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=";
    //List <News> arrayofNews = new ArrayList<News>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(NewsActivity.this)); // Image Loader Init

        //Temporary JSON
        List <News> arrayofNews = QueryUtils.fetchNewsData(URL);

        //ListView & Adapter Creation
        mNewsListView = (ListView) findViewById(R.id.list);
        final NewsAdapter mAdapter = new NewsAdapter(this,0,arrayofNews);
        mNewsListView.setAdapter(mAdapter);

        //Click Listener
        mNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Obtain current position of news item
                News currentNews = (News) mAdapter.getItem(position);
                //Converting a String URL into a Uri Object
                Uri NewsUri = Uri.parse(currentNews.getURL());
                //Create a new intent to view a URL;
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);
                startActivity(websiteIntent);

            }
        });
    }





    }


