package com.pahomovichk.weather.presenter

import android.Manifest
import android.app.Activity
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.pahomovichk.weather.App
import com.pahomovichk.weather.Constants


class CurrentLocation {

    companion object{
        val TAG = "LOCATION_"
    }

    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private val locationRequest = LocationRequest.create()?.apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    private var lat: Double = 0.0
    private var lon: Double = 0.0

    val locationCallback = object: LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                Log.d(TAG, "${location.latitude} , ${location.longitude}")
                lat = location.latitude
                lon = location.longitude
            }
        }
    }

    fun getLatitude(): Double{
        return lat
    }

    fun getLongitude(): Double{
        return lon
    }

    fun setLocationClient(activity : Activity){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    fun getLastLocation(activity : Activity) {
        if (checkPermission()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        lat = location.latitude
                        lon = location.longitude
                    }
                    else{
                        checkSettingsAndStartUpdates(activity)
                    }
                }
        }
        else{
            askPermission(activity)
        }
    }

    fun checkSettingsAndStartUpdates(activity : Activity){
        val request = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(activity)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(request.build())
        task.addOnSuccessListener { location ->
            Toast
                .makeText(
                    App.instance,
                    "${location.locationSettingsStates}",
                    Toast.LENGTH_LONG
                )
                .show()
            startLocationUpdates()
        }
        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        activity,
                        Constants.CHECK_SETTINGS_RQ
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    private fun startLocationUpdates() {
        if (checkPermission()) {
            fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper())
        }
        else{
            return
        }
    }

    fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // permissions:

    private fun askPermission(activity: Activity){
        if (ActivityCompat.checkSelfPermission(App.instance, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    Constants.LOCATION_PERMISSION_RQ
                )
            }
            else{
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    Constants.LOCATION_PERMISSION_RQ
                )
            }
        }
    }

    private fun checkPermission(): Boolean{
        if (ActivityCompat.checkSelfPermission(App.instance, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return true
    }


}