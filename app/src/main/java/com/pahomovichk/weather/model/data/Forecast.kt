package com.pahomovichk.weather.model.data

data class Forecast(
    val ico: String,
    val time: String,
    val date: String,
    val main: String,
    val temp: Double
) {
}