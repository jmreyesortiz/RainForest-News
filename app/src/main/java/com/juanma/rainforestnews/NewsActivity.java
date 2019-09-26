package com.juanma.rainforestnews;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import java.io.IOException;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private ListView mNewsListView;
    private String URL = "https://newsapi.org/v2/everything?q=rainforest&apiKey=";
    ConnectivityManager cm;
    NetworkInfo activeNetwork;
    boolean isConnected;
    ProgressBar mProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        getSupportLoaderManager().initLoader(1, null, this).forceLoad();


    }




    public static class NewsLoader extends AsyncTaskLoader<List<News>>{

        private String jsonResult;
        String input;
        private News news;

        public NewsLoader(@NonNull Context context, String url) {
            super(context);
            this.input = url;
        }

        @Nullable
        @Override
        public List<News> loadInBackground() {

            try {
                jsonResult = QueryUtils.httpRequest(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<News> listResult = QueryUtils.fetchNewsData(jsonResult);
            return listResult;

        }

    }



    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NewsLoader(NewsActivity.this,URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> news) {


        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.GONE);



        //ListView Creation
        mNewsListView = (ListView) findViewById(R.id.list);
        // Adapter Creation
        final NewsAdapter mAdapter = new NewsAdapter(this,0,news);



        //mAdapter.clear();
        if (news != null && !news.isEmpty()){
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
        else if (!isConnected){
            TextView mEmptyTextView = findViewById(R.id.empty_view);
            mEmptyTextView.setText(R.string.no_news);
            mNewsListView.setEmptyView(mEmptyTextView);
        }
        else{
            TextView mEmptyTextView = findViewById(R.id.empty_view);
            mEmptyTextView.setText(R.string.no_news);
            mNewsListView.setEmptyView(mEmptyTextView);
        }



    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        new NewsLoader(NewsActivity.this,URL);
    }




    }


