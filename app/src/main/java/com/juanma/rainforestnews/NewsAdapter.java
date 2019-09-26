package com.juanma.rainforestnews;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter {

    private String LOG_TAG = "Adapter";

    //Image Variables


    public NewsAdapter(@NonNull Context context, int resource, List<News> list) {
        super(context, resource, list);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
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
        //Date Now
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = news.getDate();
        String finalDate = parseDate(formattedDate);
        dateView.setText(finalDate);
        //Time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = news.getDate();
        String finalTime = parseTime(formattedTime);
        timeView.setText(finalTime);
        //News Source
        TextView newsSource = (TextView) listItemView.findViewById(R.id.newsSource);
        newsSource.setText(news.getNewsSource());
        //image using Picasso
        final ImageView image = (ImageView) listItemView.findViewById(R.id.newsImage);
        String imageUri = news.getImage();
        if (news!= null){
            Picasso.get().load(imageUri).into(image);
        }
        //TODO: Use Asynctask for Image loading. First Create your own way of doing it.





        return listItemView;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public  String parseDate(String input){
        java.util.Date date = Date.from( Instant.parse(input));
        String dateOut;
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
        dateOut = dateFormatter.format(date);

        return dateOut;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String parseTime(String input){
        java.util.Date date = Date.from( Instant.parse(input));
        String dateOut;
        DateFormat timeFormatter;
        timeFormatter = DateFormat.getTimeInstance(DateFormat.SHORT);
        dateOut = timeFormatter.format(date);



        return dateOut;

    }




}
