package com.tec.pokedexapp.data

import com.tec.pokedexapp.data.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonUseCase(
    private val repository: PokemonLocalRepository
){
    operator fun invoke(): Flow<List<Pokemon>>{
        return repository.getPokemons()
    }
}