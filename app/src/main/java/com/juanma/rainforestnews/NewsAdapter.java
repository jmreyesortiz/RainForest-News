package com.juanma.rainforestnews;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter {

    ImageLoader imageLoader = ImageLoader.getInstance();


    public NewsAdapter(@NonNull Context context, int resource, List<News> list) {
        super(context, resource, list);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //Current News Object
        final News news = (News) getItem(position);
        //Headline
        TextView headline = (TextView) listItemView.findViewById(R.id.headline);
        headline.setText(news.getHeadline());
        //Date
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(news.getDate());
        //News Source
        TextView newsSource = (TextView) listItemView.findViewById(R.id.newsSource);
        newsSource.setText(news.getNewsSource());
        //image
        final ImageView image = (ImageView) listItemView.findViewById(R.id.newsImage);
        String imageUri = news.getImage();

        imageLoader.loadImage(imageUri, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                imageLoader.displayImage(imageUri,image);
            }
        });

        return listItemView;
    }


    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }





}
