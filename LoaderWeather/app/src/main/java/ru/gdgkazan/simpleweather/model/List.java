package ru.gdgkazan.simpleweather.model;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Вход здесь on 08.09.2016.
 */
public class List implements Serializable {
    @SerializedName("city")
    private java.util.List<City> mCity;

    @Nullable
    public java.util.List<City> getCitys() {
        if (mCity == null || mCity.isEmpty()) {
            Log.d("SHARAPOV","null");
            return null;

        }
        return mCity;
    }

    public Integer getCol() {
        return mCity.size();
    }
}
