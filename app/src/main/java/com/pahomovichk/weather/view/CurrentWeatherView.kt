package com.pahomovichk.weather.view

import android.app.Activity
import com.pahomovichk.weather.model.data.CurrentWeather

interface CurrentWeatherView {
    fun setView(weather: CurrentWeather)
    fun getRequireActivity(): Activity
}