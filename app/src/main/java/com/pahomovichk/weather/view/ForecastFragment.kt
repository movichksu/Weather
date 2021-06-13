package com.pahomovichk.weather.view

import ForecastAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pahomovichk.weather.R
import com.pahomovichk.weather.model.Source
import com.pahomovichk.weather.model.data.Forecast
import com.pahomovichk.weather.presenter.ForecastPresenter

class ForecastFragment() : Fragment(), ForecastView {

    private lateinit var presenter: ForecastPresenter
    private lateinit var forecastView: RecyclerView
    private var forecastAdapter = ForecastAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = ForecastPresenter(Source(),this)
        presenter.getForecast()

        val root = inflater.inflate(R.layout.forecast_fragment, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastView = view.findViewById(R.id.forecast_list)

        forecastView.layoutManager = LinearLayoutManager(requireContext())
        forecastView.adapter = forecastAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun setForecast(weather: List<Forecast>) {
        forecastAdapter.setData(weather)
    }
}