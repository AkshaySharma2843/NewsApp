package com.na.newsapp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.na.newsapp.BuildConfig;
import com.na.newsapp.NewsApplication;
import com.na.newsapp.data.model.Article;

@Database(
entities = Article.class,
exportSchema = false,
version = BuildConfig.VERSION_CODE
)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();
   static NewsDatabase database;

    public static NewsDatabase createDatabaseInstance(Context context) {
        if(database == null){
          database = Room.databaseBuilder(context, NewsDatabase.class, "NEWS_DATABASE").build();
        }
        return database;
    }
}
