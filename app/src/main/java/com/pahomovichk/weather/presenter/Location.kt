package com.pahomovichk.weather.presenter

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.pahomovichk.weather.App

class CurrentLocation() {
    private lateinit var locationManager: LocationManager

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            showLocation(location)
        }

        override fun onProviderDisabled(provider: String) {}

        override fun onProviderEnabled(provider: String) {
            if (ActivityCompat.checkSelfPermission(App.instance, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(App.instance, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                return
            }
            showLocation(locationManager.getLastKnownLocation(provider))
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        }
    }

    fun fetchCurrentLocation(){
        if (ActivityCompat.checkSelfPermission(
                App.instance,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                App.instance,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            return
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000L * 10L, 10F, locationListener
        )
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000L * 10L, 10F, locationListener
        )
    }

    fun setLocationManager(){
        locationManager = App.instance.getSystemService(LOCATION_SERVICE) as LocationManager
    }

    fun showLocation(location: Location?) {
        if (location == null) return
        if (location.provider == LocationManager.GPS_PROVIDER) {
            Toast.makeText(App.instance,"GPS: ${location.latitude} - ${location.longitude}",Toast.LENGTH_LONG).show()
        } else if (location.provider == LocationManager.NETWORK_PROVIDER) {
            Toast.makeText(App.instance,"Network: ${location.latitude} - ${location.longitude}",Toast.LENGTH_LONG).show()
        }
    }



}