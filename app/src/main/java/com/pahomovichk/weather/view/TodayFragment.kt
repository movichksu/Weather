package com.pahomovichk.weather.view

import android.content.pm.PackageManager
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
import com.pahomovichk.weather.presenter.CurrentLocation
import com.pahomovichk.weather.presenter.TodayPresenter

class TodayFragment : Fragment(),CurrentWeatherView {

    private lateinit var presenter: TodayPresenter

    private lateinit var currentLocation: TextView
    private lateinit var currentTemp: TextView
    private lateinit var pop: TextView
    private lateinit var windSpeed: TextView
    private lateinit var windDeg: TextView
    private lateinit var popVolume: TextView
    private lateinit var pressure: TextView

    private val location = CurrentLocation()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = TodayPresenter(Source(),this)
        presenter.getWeather()

        location.setLocationClient(requireActivity())
        val root = inflater.inflate(R.layout.today_fragment, container, false)
        return root
    }

    override fun onStart() {
        super.onStart()
        location.getLastLocation(requireActivity())
        //location.checkSettingsAndStartUpdates(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onStop() {
        super.onStop()
        location.stopLocationUpdates()
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == Constants.LOCATION_PERMISSION_RQ) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                location.getLastLocation(requireActivity())
            }
        }
    }

}