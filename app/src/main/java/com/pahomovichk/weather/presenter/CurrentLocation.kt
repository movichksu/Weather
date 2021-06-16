package com.pahomovichk.weather.presenter

import android.app.Activity
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.pahomovichk.weather.App
import com.pahomovichk.weather.Constants
import java.lang.Exception


class CurrentLocation {

    private lateinit var fusedLocationClient : FusedLocationProviderClient

    fun setLocationClient(activity : Activity){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    fun getLocation(activity : Activity): Result {
        if (!checkLocationPermission()) {
            askPermission(activity)
            return Result.Error(Exception("The location permission was not received."))
        }
        if (!checkInternetConnection()){
            Log.d("NETWORK", "failure")
            return Result.Error(Exception("The internet is not connected. Please, turn on the internet and restart app."))
        }
            val lm = App.instance.getSystemService(LOCATION_SERVICE) as LocationManager
            val gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            if (!gps_enabled){
                Log.d("LOCATION", "failure")
                return Result.Error(Exception("Location is not connected. Please, turn on location and restart app."))
            }
        return Result.Success(fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null))
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

    private fun checkLocationPermission(): Boolean{
        if (ActivityCompat.checkSelfPermission(App.instance, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return true
    }

    private fun checkInternetConnection(): Boolean{
        val connectivityManager = App.instance.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        var capabilities : NetworkCapabilities? = null
        if (connectivityManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                } else {
                    val networks: Array<Network> = connectivityManager.getAllNetworks()
                        var i = 0
                        while (i < networks.size && capabilities == null) {
                            capabilities = connectivityManager.getNetworkCapabilities(networks.get(i))
                            i++
                        }
                }
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("INTERNET", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("INTERNET", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("INTERNET", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }


}