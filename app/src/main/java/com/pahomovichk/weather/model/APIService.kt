package com.pahomovichk.weather.model

import com.pahomovichk.weather.model.data.CurrentWeather
import com.pahomovichk.weather.model.data.CurrentWeatherResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface APIService {

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Observable<CurrentWeatherResponse>

}