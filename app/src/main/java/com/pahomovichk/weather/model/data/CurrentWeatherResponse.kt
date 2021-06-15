package com.pahomovichk.weather.model.data


import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    var base: String? = null,
    var clouds: Clouds? = null,
    var cod: Int? = null,
    var coord: Coord? = null,
    var dt: Int? = null,
    var id: Int? = null,
    var main: Main? = null,
    var rain: Rain? = null,
    var snow: Snow? = null,
    var name: String? = null,
    var sys: Sys? = null,
    var timezone: Int? = null,
    var visibility: Int? = null,
    var weather: List<Weather?>? = null,
    var wind: Wind? = null
) {
    data class Clouds(
        var all: Int? = null
    )

    data class Rain(
        @SerializedName("1h")
        var h: Double? = null
    )

    data class Snow(
        @SerializedName("1h")
        var h: Double? = null
    )

    data class Coord(
        var lat: Double? = null,
        var lon: Double? = null
    )

    data class Main(
        @SerializedName("feels_like")
        var feelsLike: Double? = null,
        @SerializedName("grnd_level")
        var grndLevel: Int? = null,
        var humidity: Int? = null,
        var pressure: Int? = null,
        @SerializedName("sea_level")
        var seaLevel: Int? = null,
        var temp: Double? = null,
        @SerializedName("temp_max")
        var tempMax: Double? = null,
        @SerializedName("temp_min")
        var tempMin: Double? = null
    )

    data class Sys(
        var country: String? = null,
        var sunrise: Int? = null,
        var sunset: Int? = null
    )

    data class Weather(
        var description: String? = null,
        var icon: String? = null,
        var id: Int? = null,
        var main: String? = null
    )

    data class Wind(
        var deg: Int? = null,
        var gust: Double? = null,
        var speed: Double? = null
    )
}