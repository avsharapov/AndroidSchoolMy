package ru.gdgkazan.simpleweather.screen.weatherlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.simpleweather.R;
import ru.gdgkazan.simpleweather.model.City;
import ru.gdgkazan.simpleweather.screen.general.LoadingDialog;
import ru.gdgkazan.simpleweather.screen.general.LoadingView;
import ru.gdgkazan.simpleweather.screen.general.SimpleDividerItemDecoration;
import ru.gdgkazan.simpleweather.screen.weather.WeatherActivity;

/**
 * @author Artur Vasilov
 */
public class WeatherListActivity extends AppCompatActivity implements CitiesAdapter.OnItemClick {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;
    private CoordinatorLayout coordLayout;
    private CitiesAdapter mAdapter;
    private String mCityName;
    private String mCityIds = "";
    private LoadingView mLoadingView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Boolean restart = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        coordLayout = (CoordinatorLayout) findViewById(R.id.coordLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this, false));
        mAdapter = new CitiesAdapter(getInitialCities(), this);
        mRecyclerView.setAdapter(mAdapter);
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
                mSwipeRefreshLayout.setRefreshing(false);
                mCityIds = "";
                restart = true;
                updateWeather();

        });
        updateWeather();

    }

    private void updateWeather() {
        mLoadingView.showLoadingIndicator();
        LoaderManager.LoaderCallbacks<City> callbacks = new IdCityCallbacks();
        if (restart) {
            for(Integer i=0;i<getInitialCities().size();i++) {

                mCityName = getInitialCities().get(i).getName();

                getSupportLoaderManager().restartLoader(i+1, Bundle.EMPTY, callbacks);
            }
        } else {
            for(Integer i=0;i<getInitialCities().size();i++) {

                mCityName = getInitialCities().get(i).getName();

                getSupportLoaderManager().initLoader(i+1, Bundle.EMPTY, callbacks);
            }
        }
    }

    private class IdCityCallbacks implements LoaderManager.LoaderCallbacks<City> {

        @Override
        public Loader<City> onCreateLoader(int id, Bundle args) {

            return new IdCityLoader(WeatherListActivity.this, mCityName);
        }

        @Override
        public void onLoadFinished(Loader<City> loader, City city) {

            getWeather(city.getId());
        }

        @Override
        public void onLoaderReset(Loader<City> loader) {

        }
    }

    private class AllWeatherCallbacks implements LoaderManager.LoaderCallbacks<java.util.List> {

        @Override
        public Loader<java.util.List> onCreateLoader(int id, Bundle args) {
            return new AllWeatherLoader(WeatherListActivity.this, mCityIds);
        }

        @Override
        public void onLoadFinished(Loader<java.util.List> loader, java.util.List listCity) {
            deliverResult(listCity);
        }

        @Override
        public void onLoaderReset(Loader<java.util.List> loader) {

        }
    }

    @Override
    public void onItemClick(@NonNull City city) {
        startActivity(WeatherActivity.makeIntent(this, city.getName()));
    }

    private void deliverResult(java.util.List listCity) {
        mLoadingView.hideLoadingIndicator();
        Snackbar.make(coordLayout,"Обновлено городов: " + listCity.size() + (restart ? " (restart)" : "(no restart)" ), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        restart = false;

    }

    private void getWeather(String cityId) {
        mCityIds = mCityIds + cityId + ",";

        if(mCityIds.split(",").length == getInitialCities().size()) {
            LoaderManager.LoaderCallbacks<java.util.List> callbacks = new AllWeatherCallbacks();
            if (restart) {

                getSupportLoaderManager().restartLoader(R.id.allweather_loader_id, Bundle.EMPTY, callbacks);
            } else {
                getSupportLoaderManager().initLoader(R.id.allweather_loader_id, Bundle.EMPTY, callbacks);
            }
        }
    }

    @NonNull
    private java.util.List<City> getInitialCities() {
        java.util.List<City> cities = new ArrayList<>();
        String[] initialCities = getResources().getStringArray(R.array.initial_cities);
        for (String city : initialCities) {
            City mCity = new City();
            mCity.setName(city);
            cities.add(mCity);
        }
        return cities;
    }
}
