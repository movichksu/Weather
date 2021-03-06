package com.pahomovichk.weather.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pahomovichk.weather.Constants
import com.pahomovichk.weather.R
import com.pahomovichk.weather.model.Source
import com.pahomovichk.weather.model.data.CurrentWeather
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
    private lateinit var failure: TextView

    private lateinit var weatherIcon: ImageView
    private lateinit var popIcon: ImageView
    private lateinit var windSpeedIcon: ImageView
    private lateinit var windDegIcon: ImageView
    private lateinit var volumeIcon: ImageView
    private lateinit var pressureIcon: ImageView

    private lateinit var shareBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = TodayPresenter(Source(),this)
        val root = inflater.inflate(R.layout.today_fragment, container, false)
        return root
    }

    override fun onStart() {
        super.onStart()
        presenter.getCurrentWeather()
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
        failure = view.findViewById(R.id.today_failure_txt)

        weatherIcon = view.findViewById(R.id.current_weather_ic)
        popIcon = view.findViewById(R.id.pop_img)
        windSpeedIcon = view.findViewById(R.id.wind_speed_img)
        windDegIcon = view.findViewById(R.id.wind_deg_img)
        volumeIcon = view.findViewById(R.id.precipitation_volume_img)
        pressureIcon = view.findViewById(R.id.pressure_img)

        shareBtn = view.findViewById(R.id.share_btn)
    }

    override fun setWeatherView(weather: CurrentWeather) {
        currentLocation.setText("${weather.name}, ${weather.country}")
        currentTemp.setText("${Math.round(weather.temp * 10) / 10}${Constants.CELSIUS} | ${weather.main}")
        pop.setText("${weather.pop}%")
        windSpeed.setText("${weather.speed} m/s")
        windDeg.setText("${Compass.defineSide(weather.deg).side}")
        popVolume.setText("${weather.volume} mm")
        pressure.setText("${weather.pressure} hPa")
        failure.setText("")

        weatherIcon.setImageResource(WeatherIcon.defineIcon(weather.ico).icon)
        popIcon.setImageResource(R.drawable.ic_shower_rain)
        windSpeedIcon.setImageResource(R.drawable.ic_windy)
        windDegIcon.setImageResource(R.drawable.ic_compass)
        volumeIcon.setImageResource(R.drawable.ic_drop)
        pressureIcon.setImageResource(R.drawable.ic_thermometer)

        shareBtn.visibility = View.VISIBLE
        shareBtn.setOnClickListener {
            val shareString = "The weather forecast in ${weather.name} for today:\n" +
                    "The temperature is ${Math.round(weather.temp * 10) / 10}??C, ${weather.main}." +
                    "Probability of precipitation ??? ${weather.pop}%. " +
                    "Wind direction is ${Compass.defineSide(weather.deg).side}, speed is ${weather.speed}m/s. " +
                    "Pressure is ${weather.pressure}hPa. "
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareString)
                type = "text/plain"
            }
            startActivity(shareIntent)
        }
    }

    override fun getRequireActivity(): Activity {
        return requireActivity()
    }

    override fun setFailureLocationView(text: String) {
        failure.setText(text)
    }

}