package com.pahomovichk.weather.view.adapter

import androidx.annotation.DrawableRes
import com.pahomovichk.weather.R

sealed class WeatherIcon(
    @DrawableRes var icon: Int
    ){
    object ClearS: WeatherIcon(R.drawable.ic_day)
    object ClearN: WeatherIcon(R.drawable.ic_night)
    object CloudyS: WeatherIcon(R.drawable.ic_cloudy_d)
    object CloudyN: WeatherIcon(R.drawable.ic_cloudy_n)
    object ScatteredClouds: WeatherIcon(R.drawable.ic_weather_cloudy)
    object ShowerRain: WeatherIcon(R.drawable.ic_shower_rain)
    object RainS: WeatherIcon(R.drawable.ic_rainy_d)
    object RainN: WeatherIcon(R.drawable.ic_rainy)
    object ThunderStorm: WeatherIcon(R.drawable.ic_thunderstorm)
    object Snow: WeatherIcon(R.drawable.ic_snowy)
    object Fog: WeatherIcon(R.drawable.ic_fog)
    object Default: WeatherIcon(R.drawable.ic_cloudy)

    companion object{
        fun defineIcon(str: String): WeatherIcon =
            when(str){
                "01d" -> WeatherIcon.ClearS
                "01n" -> WeatherIcon.ClearN
                "02d" -> WeatherIcon.CloudyS
                "02n" -> WeatherIcon.CloudyN
                "03d", "03n", "04d", "04n" -> WeatherIcon.ScatteredClouds
                "09d", "09n" -> WeatherIcon.ShowerRain
                "10d" -> WeatherIcon.RainS
                "10n" -> WeatherIcon.RainN
                "11d", "11n" -> WeatherIcon.ThunderStorm
                "13d", "13n" -> WeatherIcon.Snow
                "50d", "50n" -> WeatherIcon.Fog
                else -> WeatherIcon.Default
            }
    }
}

