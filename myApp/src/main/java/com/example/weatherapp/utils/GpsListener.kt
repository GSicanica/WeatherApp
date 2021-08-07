package com.example.weatherapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import android.provider.Settings.Secure.*
import androidx.lifecycle.LiveData
import com.example.weatherapp.R


class GpsListener(private val context: Context) : LiveData<GpsStatus>() {

    private val gpsSwitchStateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            checkGps()
        }
    }

    private fun checkGps() {
        if (isLocationEnabled()) {
            postValue(GpsStatus.Enabled())
        } else {
            postValue(GpsStatus.Disabled())
        }
    }

    private fun isLocationEnabled() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.getSystemService(LocationManager::class.java)
            .isProviderEnabled(LocationManager.GPS_PROVIDER)
    } else {
        try {
            getInt(context.contentResolver, LOCATION_MODE) != LOCATION_MODE_OFF
        } catch (e: Settings.SettingNotFoundException) {
            false
        }
    }

    private fun registerReceiver() {
        context.registerReceiver(
            gpsSwitchStateReceiver,
            IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        )
    }

    private fun unregisterReceiver() {
        context.unregisterReceiver(gpsSwitchStateReceiver)
    }

    override fun onInactive(){
        unregisterReceiver()
    }

    override fun onActive() {
        registerReceiver()
        checkGps()
    }
}

sealed class GpsStatus {
    data class Enabled(val message: Int = R.string.gps_enabled) : GpsStatus()
    data class Disabled(val message: Int = R.string.gps_disabled) : GpsStatus()
}
