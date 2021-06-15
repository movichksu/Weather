package com.pahomovichk.weather.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.pahomovichk.weather.Constants
import com.pahomovichk.weather.model.Source
import com.pahomovichk.weather.view.ForecastView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForecastPresenter(
    private val source: Source,
    private val view: ForecastView
) {
    companion object{
        val TAG = "FORECAST_PRESENTER"
    }

    private lateinit var location: CurrentLocation

    @SuppressLint("CheckResult")
    fun getForecast(){
        location = CurrentLocation()
        location.setLocationClient(view.getRequireActivity())
        location.getLocation(view.getRequireActivity()).addOnSuccessListener{ location ->
            source.getForecast(location.latitude,location.longitude, Constants.APP_ID_KEY,"metrics")
                .doOnError { error -> Log.d(TAG, "getWeather() ERROR!")}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, "Forecast was got successfully!")
                    view.setView(it)
                }
        }

    }
}