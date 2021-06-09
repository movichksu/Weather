package com.pahomovichk.weather.view

import com.pahomovichk.weather.model.data.CurrentWeather

interface WeatherView {
    fun setWeather(weather: CurrentWeather)
}