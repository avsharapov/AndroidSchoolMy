package ru.gdgkazan.simpleweather.screen.weatherlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import ru.gdgkazan.simpleweather.model.City;
import ru.gdgkazan.simpleweather.network.ApiFactory;

/**
 * Created by Вход здесь on 08.09.2016.
 */
public class IdCityLoader extends AsyncTaskLoader<City> {
    private final String mCityName;

    public IdCityLoader(Context context, @NonNull String cityName) {
        super(context);
        mCityName = cityName;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public City loadInBackground() {
        try {
            return ApiFactory.getWeatherService().getWeather(mCityName).execute().body();
        } catch (IOException e) {
            return null;
        }
    }
}
