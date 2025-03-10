package com.rigo.simondice.domain.di

import com.rigo.simondice.domain.repository.SimonGameRepository
import com.rigo.simondice.domain.service.network.IApiService
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
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesApiService(moshi: Moshi): IApiService =
        Builder()
            .run {
                baseUrl(IApiService.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
            }.create(IApiService::class.java)

    @Provides
    @Singleton
    fun provideSimonGameRepository(
        apiService: IApiService,
        moshi: Moshi
    ): SimonGameRepository = SimonGameRepository(
        apiService = apiService,
        moshi = moshi
    )

}