package com.pahomovichk.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pahomovichk.weather.R

class ForecastFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.forecast_fragment, container, false)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}