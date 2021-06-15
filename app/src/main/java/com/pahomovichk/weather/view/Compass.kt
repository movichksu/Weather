package com.pahomovichk.weather.view

import com.pahomovichk.weather.view.adapter.WeatherIcon

sealed class Compass(var side: String) {
    object N : Compass("N")
    object W : Compass("W")
    object S : Compass("S")
    object E : Compass("E")
    object NW : Compass("NW")
    object NE : Compass("NE")
    object SW : Compass("SW")
    object SE : Compass("SE")
    object Default : Compass("None")

    companion object{
        fun defineSide(deg: Int): Compass =
            when {
                (deg in 0..20) -> N
                (deg in 21..69) -> NE
                (deg in 70..110) -> E
                (deg in 111..159) -> SE
                (deg in 160..200) -> S
                (deg in 201..249) -> SW
                (deg in 250..290) -> W
                (deg in 291..339) -> NW
                (deg in 340..360) -> N
                else -> Default
            }
    }
}
