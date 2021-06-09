package com.pahomovichk.weather.presenter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.pahomovichk.weather.Constants
import com.pahomovichk.weather.model.Source
import com.pahomovichk.weather.model.data.CurrentWeather
import com.pahomovichk.weather.view.WeatherView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodayPresenter(
    private val source: Source,
    private val view: WeatherView
) {
    companion object{
        val TAG = "TODAY_PRESENTER"
    }

    @SuppressLint("CheckResult")
    fun getWeather(){
        source.getCurrentWeather(53f,27f,Constants.APP_ID_KEY,"metrics")
            .doOnError { error -> Log.d(TAG, "getWeather() ERROR!")}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "Weather was got successfully!")
                view.setWeather(it)
            }
    }
}