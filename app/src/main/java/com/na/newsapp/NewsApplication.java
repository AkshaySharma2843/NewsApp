package com.na.newsapp;

import android.app.Application;
import android.content.Context;

public class NewsApplication extends Application {

    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
