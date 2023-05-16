package com.lanazirot.simonsays.di

import android.content.Context
import androidx.room.Room
import com.lanazirot.simonsays.domain.dao.IPlayerDAO
import com.lanazirot.simonsays.domain.repository.interfaces.IApiRepository
import com.lanazirot.simonsays.domain.services.implementation.ApiService
import com.lanazirot.simonsays.domain.services.implementation.GameManager
import com.lanazirot.simonsays.domain.services.interfaces.IApiService
import com.lanazirot.simonsays.domain.services.interfaces.IGameManager
import com.lanazirot.simonsays.localdb.PlayerDB
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit.*
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlayerDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        PlayerDB::class.java,
        "player-db"
    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

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
    fun provideApiService(apiRepository: IApiRepository, playerDAO: IPlayerDAO): IApiService = ApiService(apiRepository, playerDAO)

    @Provides
    @Singleton
    fun providePlayerDao(playerDB: PlayerDB) = playerDB.playerDao()



}