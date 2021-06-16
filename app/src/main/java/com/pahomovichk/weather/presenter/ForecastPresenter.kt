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
        val rezult = location.getLocation(view.getRequireActivity())
        when(rezult){
            is Result.Success -> {
                rezult.task.addOnSuccessListener { location ->
                    source.getForecast(location.latitude, location.longitude, Constants.APP_ID_KEY, "metrics")
                        .doOnError { error ->
                            Log.d("NETWORK", "failure")
                            view.setFailureLocationView("Oops! Something went wrong.")
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { weather ->
                            Log.d(TAG, "Weather was got successfully!")
                            view.setView(weather)
                        }
                }
                    .addOnFailureListener {
                        Log.d("NETWORK", "failure")
                        view.setFailureLocationView("Oops! Something went wrong.")
                    }
            }
            is Result.Error -> {
                Log.d("NETWORK", "${rezult.exception.message}")
                view.setFailureLocationView("${rezult.exception.message}")
            }
        }

    }
}