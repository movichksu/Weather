package com.pahomovichk.weather.model.data


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    var city: City? = null,
    var cnt: Int? = null,
    var cod: String? = null,
    var list: List<Data>? = null,
    var message: Int? = null
) {
    data class City(
        var coord: Coord? = null,
        var country: String? = null,
        var id: Int? = null,
        var name: String? = null,
        var population: Int? = null,
        var sunrise: Int? = null,
        var sunset: Int? = null,
        var timezone: Int? = null
    ) {
        data class Coord(
            var lat: Double? = null,
            var lon: Double? = null
        )
    }

    data class Data(
        var clouds: Clouds? = null,
        var dt: Int? = null,
        @SerializedName("dt_txt")
        var dtTxt: String? = null,
        var main: Main? = null,
        var pop: Double? = null,
        var rain: Rain? = null,
        var sys: Sys? = null,
        var visibility: Int? = null,
        var weather: List<Weather?>? = null,
        var wind: Wind? = null
    ) {
        data class Clouds(
            var all: Int? = null
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
            @SerializedName("temp_kf")
            var tempKf: Double? = null,
            @SerializedName("temp_max")
            var tempMax: Double? = null,
            @SerializedName("temp_min")
            var tempMin: Double? = null
        )

        data class Rain(
            @SerializedName("3h")
            var h: Double? = null
        )

        data class Sys(
            var pod: String? = null
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
}