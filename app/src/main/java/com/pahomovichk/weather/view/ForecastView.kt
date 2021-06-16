package com.pahomovichk.weather.view

import android.app.Activity
import com.pahomovichk.weather.model.data.CurrentWeather
import com.pahomovichk.weather.model.data.Forecast

interface ForecastView {
    fun setView(weather: List<Forecast>)
    fun getRequireActivity(): Activity
    fun setFailureLocationView(text: String)
}