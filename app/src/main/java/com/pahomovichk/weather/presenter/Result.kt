package com.pahomovichk.weather.presenter

import android.location.Location
import com.google.android.gms.tasks.Task

sealed class Result {
    class Success(val task: Task<Location>) : Result()
    class Error(val exception: Throwable) : Result()
}
