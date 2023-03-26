package com.rigo9412.poketest.ui.main

import com.rigo9412.poketest.data.model.Pokemon

sealed class MainAppState {
    object Loading: MainAppState()
    object Error: MainAppState()
    class Done(val pokedex: List<Pokemon>): MainAppState()
}