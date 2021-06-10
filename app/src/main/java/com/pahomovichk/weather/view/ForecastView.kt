package com.pahomovichk.weather.view

import com.pahomovichk.weather.model.data.CurrentWeather
import com.pahomovichk.weather.model.data.Forecast

interface ForecastView {
    fun setForecast(weather: List<Forecast>)
}