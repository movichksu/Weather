package com.pahomovichk.weather.view

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pahomovichk.weather.R
import com.pahomovichk.weather.model.Source
import com.pahomovichk.weather.model.data.Forecast
import com.pahomovichk.weather.presenter.ForecastPresenter
import com.pahomovichk.weather.presenter.TodayPresenter

class ForecastFragment() : Fragment(), ForecastView {

    private lateinit var parsenter: ForecastPresenter

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parsenter = ForecastPresenter(Source(),this)
        parsenter.getForecast()

        val root = inflater.inflate(R.layout.forecast_fragment, container, false)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun setForecast(weather: List<Forecast>) {

    }
}