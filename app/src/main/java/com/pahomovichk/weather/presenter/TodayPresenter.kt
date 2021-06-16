package com.pahomovichk.weather.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.pahomovichk.weather.App
import com.pahomovichk.weather.Constants
import com.pahomovichk.weather.R
import com.pahomovichk.weather.model.Source
import com.pahomovichk.weather.view.CurrentWeatherView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodayPresenter(
    private val source: Source,
    private val view: CurrentWeatherView
) {
    companion object{
        val TAG = "TODAY_PRESENTER"
    }

    private lateinit var location: CurrentLocation

    @SuppressLint("CheckResult")
    fun getCurrentWeather(){
        location = CurrentLocation()
        location.setLocationClient(view.getRequireActivity())

        val rezult = location.getLocation(view.getRequireActivity())
        when(rezult){
            is Result.Success -> {
                rezult.task.addOnSuccessListener { location ->
                    source.getCurrentWeather(location.latitude, location.longitude, Constants.APP_ID_KEY, "metrics")
                        .doOnError { error ->
                            Log.d("NETWORK", "failure")
                            view.setFailureLocationView(App.instance.getString(R.string.exception_unknown))
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { weather ->
                            view.setWeatherView(weather)
                        }
                }
                    .addOnFailureListener { error ->
                        Log.d("NETWORK", "failure")
                        view.setFailureLocationView(App.instance.getString(R.string.exception_unknown))
                    }
            }
            is Result.Error -> {
                Log.d("NETWORK", "${rezult.exception.message}")
                view.setFailureLocationView("${rezult.exception.message}")
            }
        }
    }
}