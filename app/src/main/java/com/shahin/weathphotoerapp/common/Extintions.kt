package com.shahin.weathphotoerapp.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.shahin.weathphotoerapp.ui.addItem.LOCATION_COARSE_PERMISSION
import com.shahin.weathphotoerapp.ui.addItem.LOCATION_FINE_PERMISSION
import com.shahin.weathphotoerapp.ui.addItem.LOCATION_PERMISSION_REQUEST_CODE

const val GPS_REQUEST_CODE = 4043


fun Activity.checkGps(): Boolean {
    val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        true
    } else {
        showGPSDisabledAlertToUser()
        false
    }
}

private fun Activity.showGPSDisabledAlertToUser() {
    val alertDialogBuilder = AlertDialog.Builder(this)

    alertDialogBuilder.setMessage("please open gps")
        .setCancelable(false)
        .setPositiveButton("open gps") { dialog, id ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivityForResult(
                intent,
                GPS_REQUEST_CODE
            )
        }
    alertDialogBuilder.setNegativeButton(
        "Cancel"
    ) { dialog, id -> }
    val alert = alertDialogBuilder.create()

    alert.show()
}

fun Activity.checkLocationPermissions(): Boolean {
    val permissions = arrayOf(
        LOCATION_COARSE_PERMISSION,
        LOCATION_FINE_PERMISSION
    )

    return if (ContextCompat.checkSelfPermission(
            this,
            LOCATION_COARSE_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(
            this,
            LOCATION_FINE_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        true
    } else {

        ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE)
        false
    }
}