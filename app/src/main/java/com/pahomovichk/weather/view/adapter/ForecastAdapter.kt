package com.pahomovichk.weather.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pahomovichk.weather.Constants
import com.pahomovichk.weather.R
import com.pahomovichk.weather.model.data.Forecast

class ForecastAdapter internal constructor(
    private var forecast: List<Forecast>
) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.weather_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val weather = forecast[position]
        viewHolder.description.text = weather.main
        viewHolder.time.text = weather.time
        viewHolder.temperature.text = "${Math.round(weather.temp * 10) / 10}${Constants.CELSIUS}"
    }

    override fun getItemCount() = forecast.size

    fun setData(data: List<Forecast>) {
        this.forecast = data
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(R.id.weather_container)
        val description: TextView = view.findViewById(R.id.description)
        val time: TextView = view.findViewById(R.id.time)
        val temperature: TextView = view.findViewById(R.id.temperature)
    }

}