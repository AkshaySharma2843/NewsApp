package com.na.newsapp.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.na.newsapp.data.model.DeviceConfig;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDeviceConfig(DeviceConfig deviceConfig);
}
