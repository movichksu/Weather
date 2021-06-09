package com.pahomovichk.weather.model

import com.google.gson.GsonBuilder
import com.pahomovichk.weather.BuildConfig
import com.pahomovichk.weather.Constants
import com.pahomovichk.weather.model.data.CurrentWeather
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Source {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.WEATHER_BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
    val apiService: APIService = retrofit.create(APIService::class.java)

    fun getCurrentWeather(lat: Float, lon: Float, appId: String, units: String): Observable<CurrentWeather> {
        return apiService
            .getCurrentWeather(lat,lon,appId,units)
            .map { response ->
                CurrentWeather(
                    response.name.orEmpty(),
                    response.sys?.country.orEmpty(),
                    response.weather?.get(0)?.main.orEmpty(),
                    response.main?.temp?.minus(273) ?: 0.0,
                    response.clouds?.all?: 0,
                    response.main?.pressure ?: 0,
                    response.wind?.speed ?: 0.0,
                    response.wind?.deg ?: 0
                )
            }
    }
}

