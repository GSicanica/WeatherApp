package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.utils.hasPermission
import com.example.weatherapp.utils.requestPermissionWithRationale
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var parentLayout: View
    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }
    private var cancellationTokenSource = CancellationTokenSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parentLayout = findViewById(android.R.id.content)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "onRequestPermissionResult()")

        if (requestCode == REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() ->

                    Log.d(TAG, "User interaction was cancelled.")

                grantResults[0] == PackageManager.PERMISSION_GRANTED ->
                    Snackbar.make(
                        parentLayout,
                        R.string.permission_approved_explanation,
                        Snackbar.LENGTH_LONG
                    ).show()

                else -> {
                    Snackbar.make(
                        parentLayout,
                        R.string.fine_permission_denied_explanation,
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(R.string.settings) {
                            // Build intent that displays the App settings screen.
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts(
                                "package",
                                BuildConfig.APPLICATION_ID,
                                null
                            )
                            intent.data = uri
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }.show()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun requestCurrentLocation(currentLocation: (Location) -> Unit, showToast: () -> Unit) {
        val permissionApproved =
            applicationContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)

        //while is true can access to location without permission request
        if (permissionApproved) {
            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )
            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                if (task.isSuccessful && task.result != null) {
                    val location: Location = task.result
                    //api call by current location

                    currentLocation(location)

                } else {
                    val exception = task.exception
                    if (exception != null) {
                        Log.e("MainActivity", exception.localizedMessage)
                    }
                    //showToast()
                }
            }
        } else {
            requestPermissionWithRationale(
                Manifest.permission.ACCESS_FINE_LOCATION,
                REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE,
                fineLocationRationalSnackbar
            )
        }
        // showToast()
    }

    private val fineLocationRationalSnackbar by lazy {
        Snackbar.make(
            findViewById(android.R.id.content),
            R.string.fine_location_permission_rationale,
            Snackbar.LENGTH_LONG
        ).setAction(R.string.ok) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onStop() {
        super.onStop()
        cancellationTokenSource.cancel()
    }

    override fun onResume() {
        super.onResume()
        cancellationTokenSource = CancellationTokenSource()
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE = 34
    }
}