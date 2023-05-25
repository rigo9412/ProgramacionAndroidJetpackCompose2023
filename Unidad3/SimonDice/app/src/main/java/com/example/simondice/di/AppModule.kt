package com.example.simondice.di

import android.content.Context
import androidx.room.Room
import com.example.simondice.dao.PlayerDao
import com.example.simondice.dao.SimonDB
import com.example.simondice.domain.SimonStore
import com.example.simondice.domain.service.network.IApiService
import com.example.simondice.repository.SimonGameRepository
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
        playerDao: PlayerDao,
        store: SimonStore
    ): SimonGameRepository = SimonGameRepository(
        apiService = apiService,
        moshi = moshi,
        db = playerDao,
        store = store
    )

    @Provides
    @Singleton
    fun provideSimonDB(@ApplicationContext context : Context) = Room.databaseBuilder(
        context,SimonDB::class.java,"SIMON-DB")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providePlayerDao(demoDatabase: SimonDB): PlayerDao {
        return demoDatabase.playerDao()
    }

    @Provides
    @Singleton
    fun provideStore(@ApplicationContext context : Context) = SimonStore(context)
}
