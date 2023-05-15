package com.tec.pokedexapp.domain.di

import com.tec.pokedexapp.domain.repository.UserRepositoryImp
import com.tec.pokedexapp.domain.repository.UserRespository
import com.tec.pokedexapp.domain.source.RestDataSource
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
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun userRepository(repo:UserRepositoryImp) : UserRespository
}