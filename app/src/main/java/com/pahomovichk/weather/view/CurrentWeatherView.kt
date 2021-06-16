package com.pahomovichk.weather.view

import android.app.Activity
import com.pahomovichk.weather.model.data.CurrentWeather

interface CurrentWeatherView {
    fun setWeatherView(weather: CurrentWeather)
    fun getRequireActivity(): Activity
    fun setFailureLocationView(text: String)
}