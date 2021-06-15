package com.pahomovichk.weather.model.data

data class CurrentWeather(
    val ico: String,
    val name: String,
    val country: String,
    val main: String,
    val temp: Double,
    val pop: Int,
    val volume: Double,
    val pressure: Int,
    val speed: Double,
    val deg: Int
)