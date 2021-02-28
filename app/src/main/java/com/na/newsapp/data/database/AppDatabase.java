package com.na.newsapp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.na.newsapp.BuildConfig;
import com.na.newsapp.data.model.DeviceConfig;

@Database(
        entities = DeviceConfig.class,
        exportSchema = false,
        version = BuildConfig.VERSION_CODE
)
public abstract class AppDatabase extends RoomDatabase {

   static AppDatabase database;
    public abstract AppDao appDao();

    public static AppDatabase createDatabaseInstance(Context context){
        if(database == null){
            database = Room.databaseBuilder(context,AppDatabase.class, "APP_DATABASE").build();
        }
        return database;
    }
}
