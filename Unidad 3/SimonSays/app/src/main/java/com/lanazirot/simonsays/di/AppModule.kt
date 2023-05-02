package com.lanazirot.simonsays.di

import com.lanazirot.simonsays.domain.repository.interfaces.IApiRepository
import com.lanazirot.simonsays.domain.services.implementation.ApiService
import com.lanazirot.simonsays.domain.services.implementation.GameManager
import com.lanazirot.simonsays.domain.services.interfaces.IApiService
import com.lanazirot.simonsays.domain.services.interfaces.IGameManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit.*
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGameManager(): IGameManager = GameManager()

    @Provides
    @Singleton
    fun provideApiRepository(moshi: Moshi): IApiRepository = Builder().run {
        baseUrl(IApiRepository.URL).addConverterFactory(
            MoshiConverterFactory.create(moshi)
        ).build().create(IApiRepository::class.java)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideApiService(apiRepository: IApiRepository): IApiService = ApiService(apiRepository)


}