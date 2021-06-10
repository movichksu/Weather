package com.pahomovichk.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pahomovichk.weather.Constants
import com.pahomovichk.weather.R
import com.pahomovichk.weather.model.Source
import com.pahomovichk.weather.model.data.CurrentWeather
import com.pahomovichk.weather.presenter.TodayPresenter

class TodayFragment : Fragment(),CurrentWeatherView {

    private lateinit var parsenter: TodayPresenter

    private lateinit var currentLocation: TextView
    private lateinit var currentTemp: TextView
    private lateinit var pop: TextView
    private lateinit var windSpeed: TextView
    private lateinit var windDeg: TextView
    private lateinit var popVolume: TextView
    private lateinit var pressure: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parsenter = TodayPresenter(Source(),this)
        parsenter.getWeather()

        val root = inflater.inflate(R.layout.today_fragment, container, false)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentLocation = view.findViewById(R.id.current_weather_location)
        currentTemp = view.findViewById(R.id.current_weather_temp)
        pop = view.findViewById(R.id.pop_txt)
        windSpeed = view.findViewById(R.id.wind_speed_txt)
        windDeg = view.findViewById(R.id.wind_deg_txt)
        popVolume = view.findViewById(R.id.precipitation_volume_txt)
        pressure = view.findViewById(R.id.pressure_txt)
    }

    override fun setWeather(weather: CurrentWeather) {
        currentLocation.setText("${weather.name}, ${weather.country}")
        currentTemp.setText("${Math.round(weather.temp * 10) / 10}${Constants.CELSIUS} | ${weather.main}")
        pop.setText("${weather.pop}%")
        windSpeed.setText("${weather.speed} km/h")
        windDeg.setText("${weather.deg} deg")
        popVolume.setText("${weather.speed} mm")
        pressure.setText("${weather.pressure} hPa")
    }

}