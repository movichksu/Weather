package com.pahomovichk.weather.model.data

data class CurrentWeather(
    val name: String,
    val main: String,
    val temp: Double,
    val pressure: Int,
    val speed: Double,
    val deg: Int
)