package com.lanazirot.gpsdemo.domain.interfaces.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface ILocationService {
    /*
    *  A flow to get the last known location given an interval
    *
    * @param interval: The interval in milliseconds
    * */
    fun getLocationUpdates(interval: Long): Flow<Location>
    class LocationException(message: String) : Exception()
    class GPSDisabledException(message: String) : Exception()
    class NetworkDisabledException(message: String) : Exception()
}