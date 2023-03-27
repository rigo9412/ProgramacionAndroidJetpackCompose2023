package com.lanazirot.pokedex.domain.interfaces

import com.lanazirot.pokedex.domain.models.PokemonMapped

interface IPokemonService {
    suspend fun getPokemons(): List<PokemonMapped>
    suspend fun getPokemon(id: Int): PokemonMapped
}