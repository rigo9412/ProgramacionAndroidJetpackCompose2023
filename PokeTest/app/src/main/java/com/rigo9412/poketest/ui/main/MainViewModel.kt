package com.rigo9412.poketest.ui.main

import android.content.Context
import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rigo9412.poketest.data.model.PokemonEntity
import com.rigo9412.poketest.data.PokemonLocalRepository
import com.rigo9412.poketest.data.model.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val manager: AssetManager,
    private val repo: PokemonLocalRepository = PokemonLocalRepository(manager)
): ViewModel() {
    private val _mainUIState = MutableStateFlow<MainAppState>(MainAppState.Loading)
    val mainUiState: StateFlow<MainAppState> = _mainUIState

    private val _mainPokemonData = MutableStateFlow<List<Pokemon>?>(null)
    val mainPokemonData: StateFlow<List<Pokemon>?> = _mainPokemonData


    init {
        onInitPokedex()
    }

    fun onInitPokedex() {
        viewModelScope.launch {
            repo.getPokemons()
                .collect { result ->
                    _mainPokemonData.value =  result
                    _mainUIState.value = MainAppState.Done(result)
                }
        }
    }
}


