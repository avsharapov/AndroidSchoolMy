package ru.gdgkazan.simpleweather.screen.weatherlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import ru.gdgkazan.simpleweather.model.City;
import ru.gdgkazan.simpleweather.network.ApiFactory;

/**
 * Created by shara on 06.09.2016.
 */
public class AllWeatherLoader extends AsyncTaskLoader<java.util.List> {
    private final String mCityIds;

    public AllWeatherLoader(Context context, @NonNull String cityIds) {
        super(context);
        mCityIds = cityIds;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public java.util.List loadInBackground() {
        ResponseBody responseBody;
        try {

            responseBody = ApiFactory.getWeatherService().getAllWeather(mCityIds).execute().body();
            JSONObject data = new JSONObject(responseBody.string());
            JSONArray list = data.getJSONArray("list");

            java.util.List<City> lst = new ArrayList<City>();
            for(int i=0;i<list.length();i++){

                lst.add(new City(list.getJSONObject(i).getString("name")));
            }

            return lst;
        } catch (Exception e) {
            return null;
        }
    }
}
