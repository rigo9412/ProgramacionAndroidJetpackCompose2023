package com.example.simondicef.domain.di

import android.content.Context
import androidx.room.Room
import com.example.simondicef.domain.dao.UserDao
import com.example.simondicef.domain.source.RestDataSource
import com.example.simondicef.domain.source.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule{
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://simon-game-api-production.up.railway.app/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit{
        return  Retrofit.Builder()
            .addConverterFactory((GsonConverterFactory.create()))
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): RestDataSource = retrofit.create(RestDataSource::class.java)

    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao{
        return userDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appcontext: Context): UserDatabase{
        return Room.databaseBuilder(
            appcontext,
            UserDatabase::class.java,
            "user-database"
        ).build()
    }
}