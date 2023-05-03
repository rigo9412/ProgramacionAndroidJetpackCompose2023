package com.example.simondicef.domain.di

import com.example.simondicef.domain.repository.UserRepositoryImp
import com.example.simondicef.domain.repository.UserRespository
import com.example.simondicef.domain.source.RestDataSource
import dagger.Binds
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
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun userRepository(repo: UserRepositoryImp): UserRespository

}