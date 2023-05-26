package com.lanazirot.gpsdemo.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.lanazirot.gpsdemo.application.implementation.location.LocationClient
import com.lanazirot.gpsdemo.domain.interfaces.location.ILocationService
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