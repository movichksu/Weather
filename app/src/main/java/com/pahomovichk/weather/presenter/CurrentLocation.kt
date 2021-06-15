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

    fun setLocationClient(activity : Activity){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    fun getLocation(activity : Activity): Task<Location> {
        if (!checkPermission()) {
            askPermission(activity)
        }
        return fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
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