package com.ezequiel.simondice.domain.di

import android.content.Context
import androidx.room.Room
import com.ezequiel.simondice.domain.dao.PlayerDao
import com.ezequiel.simondice.domain.dao.SimonDB
import com.ezequiel.simondice.domain.service.network.IApiService
import com.ezequiel.simondice.repositorio.SimonGameRepository
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
        moshi: Moshi,
        playerDao: PlayerDao
    ): SimonGameRepository = SimonGameRepository(
        apiService = apiService,
        moshi = moshi,
        db = playerDao
    )

    @Provides
    @Singleton
    fun providePlayerDao(playerDB: SimonDB) = playerDB.playerDao()


    @Provides
    @Singleton
    fun provide(@ApplicationContext context : Context) = Room.databaseBuilder(
        context, SimonDB::class.java, "SIMON-DB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

//    @Provides
//    @Singleton
//    fun providePlayerDao(demoDatabase:SimonDB):PlayerDao {
//        return demoDatabase.playerDao()
//    }
}