package com.pahomovichk.weather.view

import androidx.annotation.DrawableRes
import com.pahomovichk.weather.R

sealed class WeatherIcon(
    @DrawableRes var icon: Int
    ){
    object ClearS: WeatherIcon(R.drawable.ic_day)
    object ClearN: WeatherIcon(R.drawable.ic_night)
    object CloudyS: WeatherIcon(R.drawable.ic_cloudy_d)
    object CloudyN: WeatherIcon(R.drawable.ic_cloudy_n)
    object ScatteredClouds: WeatherIcon(R.drawable.ic_cloudy)
    object ShowerRainS: WeatherIcon(R.drawable.ic_shower_rain_d)
    object ShowerRainN: WeatherIcon(R.drawable.ic_shower_rain_n)
    object RainS: WeatherIcon(R.drawable.ic_rain_d)
    object RainN: WeatherIcon(R.drawable.ic_rain_n)
    object ThunderStorm: WeatherIcon(R.drawable.ic_thunderstorm)
    object Snow: WeatherIcon(R.drawable.ic_snowy)
    object Fog: WeatherIcon(R.drawable.ic_fog)
    object Default: WeatherIcon(R.drawable.ic_cloudy)

    companion object{
        fun defineIcon(str: String): WeatherIcon =
            when(str){
                "01d" -> ClearS
                "01n" -> ClearN
                "02d", "03d" -> CloudyS
                "02n", "03n" -> CloudyN
                "04d", "04n" -> ScatteredClouds
                "09d" -> ShowerRainS
                "09n" -> ShowerRainN
                "10d" -> RainS
                "10n" -> RainN
                "11d", "11n" -> ThunderStorm
                "13d", "13n" -> Snow
                "50d", "50n" -> Fog
                else -> Default
            }
    }
}

