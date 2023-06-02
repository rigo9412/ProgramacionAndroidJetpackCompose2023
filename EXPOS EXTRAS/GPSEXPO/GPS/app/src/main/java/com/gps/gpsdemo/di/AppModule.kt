package com.gps.gpsdemo.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.gps.gpsdemo.application.implementation.location.LocationClient
import com.gps.gpsdemo.domain.interfaces.location.ILocationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocationClient(@ApplicationContext context: Context): ILocationService =
        LocationClient(
            context, LocationServices.getFusedLocationProviderClient(context)
        )
}