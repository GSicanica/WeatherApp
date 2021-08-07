package com.example.weatherapp.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.weatherapp.data.responseById.Forecast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun Context.hasPermission(permission: String): Boolean {
    // Background permissions didn't exit prior to Q, so it's approved by default.
    if (permission == Manifest.permission.ACCESS_BACKGROUND_LOCATION &&
        android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q
    ) {
        return true
    }
    return ActivityCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
}

@RequiresApi(Build.VERSION_CODES.M)
fun Activity.requestPermissionWithRationale(
    permission: String,
    requestCode: Int,
    snackbar: Snackbar
) {
    val provideRationale = shouldShowRequestPermissionRationale(permission)
    if (provideRationale) {
        snackbar.show()
    } else {
        requestPermissions(arrayOf(permission), requestCode)
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)

@SuppressLint("NewApi")
fun getDayOfWeek(s: String): String {
    val dtfInput: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val dtfOutput: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)
    return LocalDate.parse(s, dtfInput).format(dtfOutput)
}

fun currentDay(): String? {
    val calendar = Calendar.getInstance()
    val date = calendar.time
    return (SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time))
}

fun nextDay(): String {
    val calendar = Calendar.getInstance()
    calendar.time
    calendar.add(Calendar.DATE, 1)
    return (SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.time))
}


fun getEveryEightyElementFromList(list: List<Forecast>): List<Forecast> {
    val newList = mutableListOf<Forecast>()
    var i = 7// to skip first day
    while (i < list.size) {
        newList.add(list[i])
        i += 8
    }
    return newList
}