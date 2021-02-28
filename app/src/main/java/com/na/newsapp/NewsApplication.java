package com.na.newsapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;

import com.na.newsapp.data.database.AppDao;
import com.na.newsapp.data.database.AppDatabase;
import com.na.newsapp.data.model.DeviceConfig;

public class NewsApplication extends Application {

    AppDao appDao;

    @Override
    public void onCreate() {
        super.onCreate();
        getDeviceId();
    }

    private void getDeviceId(){
        @SuppressLint("HardwareIds")
        String deviceId =  Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
       AppDatabase appDatabase = AppDatabase.createDatabaseInstance(this);
       appDao = appDatabase.appDao();
        DeviceConfig deviceConfig = new DeviceConfig();
        deviceConfig.setDeviceId(deviceId);
        storeDeviceId(deviceConfig);

    }

    private void storeDeviceId(DeviceConfig deviceId) {
        class AsyncTaskSaver extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                appDao.addDeviceConfig(deviceId);
                return null;
            }
        }
        AsyncTaskSaver asyncTask = new AsyncTaskSaver();
        asyncTask.execute();
    }


}
