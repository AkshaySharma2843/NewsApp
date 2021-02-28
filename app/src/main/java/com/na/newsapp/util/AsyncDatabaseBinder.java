package com.na.newsapp.util;

import android.os.AsyncTask;

import com.na.newsapp.data.model.Article;

public class AsyncDatabaseBinder extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    public void addDataInBackground(Article article){

    }
}
