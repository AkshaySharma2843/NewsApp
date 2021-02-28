package com.na.newsapp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.na.newsapp.util.TableConstants;

@Entity(tableName = TableConstants.DEVICE_CONFIG_TABLE)
public class DeviceConfig {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
