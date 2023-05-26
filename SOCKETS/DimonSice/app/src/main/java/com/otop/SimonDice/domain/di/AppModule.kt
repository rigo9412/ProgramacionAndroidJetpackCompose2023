package com.otop.SimonDice.domain.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.otop.SimonDice.domain.Repository.SimonGameRepository
import com.otop.SimonDice.domain.dao.PlayerDao
import com.otop.SimonDice.domain.dao.SimonDB
import com.otop.SimonDice.domain.services.network.IApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, SimonDB:: class.java, "SimonDB"
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()




    @Singleton
    @Provides
    fun providesPlayerDao(demoDatabase: SimonDB): PlayerDao{
        return demoDatabase.playerDao()
    }



    @Provides @Singleton fun provideMoshi(): Moshi =
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

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

    @Singleton
    @Provides
    fun providesPlayerDao(demoDatabase: SimonDB): PlayerDao{
        return demoDatabase.playerDao()
    }

}