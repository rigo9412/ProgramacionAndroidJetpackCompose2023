package com.lanazirot.gpsdemo.di

import android.content.Context
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import com.lanazirot.gpsdemo.application.implementation.geofencing.GeofenceService
import com.lanazirot.gpsdemo.domain.interfaces.geofencing.IGeofenceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GeofencingModule {

    @Singleton
    @Provides
    fun provideGeofencingClient(@ApplicationContext context: Context): GeofencingClient {
        return LocationServices.getGeofencingClient(context)
    }

    @Singleton
    @Provides
    fun provideGeofencingService(@ApplicationContext context: Context): IGeofenceService = GeofenceService(context)

}