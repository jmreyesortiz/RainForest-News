package com.juanma.rainforestnews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    public static final int OPERATION_SEARCH_LOADER_ID = 22; // Loader Id


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        //ToolBar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); //Changing the color of the Bar
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Intent intent = new Intent(this, NewsActivity.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Toast.makeText(this,"33333322", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this,"322234234", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
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


    private void makeQuery(String url){

        Bundle queryBundle = new Bundle();
        //queryBundle.putString();

    }


}


