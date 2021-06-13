package com.pahomovichk.weather.view

import com.pahomovichk.weather.model.data.CurrentWeather

interface CurrentWeatherView {
    fun setWeather(weather: CurrentWeather)
}