package com.lanazirot.examen1.di


import com.lanazirot.examen1.domain.services.implementation.GatoService
import com.lanazirot.examen1.domain.services.interfaces.IGatoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGatoService(): IGatoService = GatoService()
}