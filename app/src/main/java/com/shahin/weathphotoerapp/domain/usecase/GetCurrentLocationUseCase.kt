package com.shahin.weathphotoerapp.domain.usecase

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val  fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    suspend operator fun invoke(): Flow<Location?> = callbackFlow {

        val locationCallback= object : LocationCallback() {
            override fun onLocationAvailability(availability: LocationAvailability) {
                super.onLocationAvailability(availability)
                if (!availability.isLocationAvailable){
                    close(Exception("Can't find location"))
                }
            }
            override fun onLocationResult(result: LocationResult) {
                result.let {
                    for (location in result.locations) {
                        trySend(location)
                    }
                }
            }
        }
        val locationUpdateRequest = createLocationRequest()


        fusedLocationProviderClient.requestLocationUpdates(locationUpdateRequest,locationCallback,null)

        awaitClose{
            fusedLocationProviderClient.removeLocationUpdates(
                locationCallback
            )
        }

    }


    private fun createLocationRequest(): LocationRequest {
        val locationUpdateRequest =  LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).apply {
            setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
            setWaitForAccurateLocation(true)
        }.build()

        return locationUpdateRequest
    }

}