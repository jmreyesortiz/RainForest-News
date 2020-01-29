package com.juanma.rainforestnews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private ListView mNewsListView;
    private static final String FIRST_URL = "https://newsapi.org/v2/everything?q=rainforest&apiKey=";
    private static final String OTHER_URL = "https://newsapi.org/v2/everything?q=Slayer&apiKey=";

    ConnectivityManager cm;
    NetworkInfo activeNetwork;
    boolean isConnected;
    ProgressBar mProgressBar;
    public static final int LOADER_ID = 22; // Loader Id
    private static final String TAG = "NewsActivity";
    private String mInputURL;

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
        Objects.requireNonNull(mToolbar.getOverflowIcon()).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); //Changing the color of the Bar
        LoaderManager.getInstance(this).initLoader(LOADER_ID, null,this).forceLoad(); // Initializes Loader.

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String forest = "https://newsapi.org/v2/everything?q=Forest&apiKey=" ;
        String forestation ="https://newsapi.org/v2/everything?q=Forestation&apiKey=" ;
        String rainforest = "https://newsapi.org/v2/everything?q=RainForest&apiKey=";

        switch(item.getItemId()){
            case R.id.item1:
                makeQuery(forest);
                return true;
            case R.id.item2:
                makeQuery(forestation);
                return true;
            case R.id.item3:
                makeQuery(rainforest);
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
        String a_url;

        if (args == null){
            return new NewsLoader(NewsActivity.this, FIRST_URL);
        }
        else{

            a_url = BundleToString(args);
            Log.i(TAG, a_url);
            return new NewsLoader(NewsActivity.this, a_url);
        }

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
                    //Converting a String FIRST_URL into a Uri Object
                    Uri NewsUri = Uri.parse(currentNews.getURL());
                    //Create a new intent to view a FIRST_URL;
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
        new NewsLoader(NewsActivity.this, FIRST_URL);
    }



    public void makeQuery(String url){

        mProgressBar.setVisibility(View.VISIBLE);
        Bundle queryBundle = new Bundle();
        queryBundle.putString(FIRST_URL, url);
        LoaderManager loaderManager = (LoaderManager) LoaderManager.getInstance(NewsActivity.this);
        loaderManager.restartLoader(LOADER_ID, queryBundle,this).forceLoad();

    }

    public static String BundleToString(Bundle bundle) {

        if (bundle == null) {
            return FIRST_URL;
        } else {
            String a_string = "";
            for (String key : bundle.keySet()) {
                a_string += bundle.get(key);
            }
            return a_string;

        }
    }

}


