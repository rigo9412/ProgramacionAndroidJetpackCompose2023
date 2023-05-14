package com.tec.pokedexapp.domain.di

import com.tec.pokedexapp.domain.source.RestDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    @Named("BaseURL")
    fun provideBaseURL() = "https://pokedextop-8451f-default-rtdb.firebaseio.com/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseURL") baseUrl: String): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory((GsonConverterFactory.create()))
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): RestDataSource = retrofit.create(RestDataSource::class.java)
}