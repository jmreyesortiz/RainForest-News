package com.juanma.rainforestnews;

import android.os.Parcelable;

public class News  {


    private String mHeadline;
    private String mDate;
    private String mImage;
    private String mNewsSource;
    private String mURL;



    public News(String headline, String date, String imageURL, String newsSource, String URL) {
        mHeadline = headline;
        mDate = date;
        mImage = imageURL;
        mNewsSource = newsSource;
        mURL = URL;
    }

    public News(String URL) {
        mURL = URL;
    }

    public String getURL() {
        return mURL;
    }

    public void setURL(String URL) {
        mURL = URL;
    }


    public String getNewsSource() {
        return mNewsSource;
    }

    public void setNewsSource(String newsSource) {
        this.mNewsSource = newsSource;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public void setHeadline(String headline) {
        this.mHeadline = headline;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }


}
