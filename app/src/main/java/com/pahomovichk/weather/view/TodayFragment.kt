package com.pahomovichk.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pahomovichk.weather.R
import com.pahomovichk.weather.model.Source
import com.pahomovichk.weather.model.data.CurrentWeather
import com.pahomovichk.weather.presenter.TodayPresenter
import org.w3c.dom.Text

class TodayFragment : Fragment(),WeatherView {

    private lateinit var parsenter: TodayPresenter
    private lateinit var weather: CurrentWeather
    private lateinit var text: TextView

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
        text = view.findViewById<TextView>(R.id.today_text)
    }

    override fun setWeather(weather: CurrentWeather) {
        Toast
            .makeText(this.context, "${weather.name}, ${weather.temp}", Toast.LENGTH_SHORT)
            .show()
        text.setText("${weather.name}, ${weather.temp}")
    }

}