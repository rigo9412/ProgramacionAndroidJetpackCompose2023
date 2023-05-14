package com.tec.pokedexapp.domain.di

import android.content.Context
import androidx.room.Room
import com.tec.pokedexapp.data.PokemonLocalRepository
import com.tec.pokedexapp.data.source.PokemonLocalAPI
import com.tec.pokedexapp.domain.dao.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
//        return Room.databaseBuilder(
//            context,
//            AppDatabase::class.java,
//            "pokemon-database"
//        ).build()
//    }
//
//    @Provides
//    fun providePokemonLocalAPI(): PokemonLocalAPI {
//        return PokemonLocalAPI()
//    }
//
//    @Provides
//    fun providePokemonLocalRepository(
//        @ApplicationContext context: Context,
//        pokemonLocalAPI: PokemonLocalAPI
//    ): PokemonLocalRepository {
//        return PokemonLocalRepository(context.assets, pokemonLocalAPI)
//    }
//}