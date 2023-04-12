package com.tec.pokedexapp.ui.navigator.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _topScores = MutableStateFlow<List<Int>>(listOf(0,0,0))
    val topScores: StateFlow<List<Int>> = _topScores

    private val _tries = MutableStateFlow(0)
    val tries: StateFlow<Int> = _tries

    fun addScore(score : Int){
        _topScores.value = _topScores.value + score
        _topScores.value = _topScores.value.sortedDescending()
        if(_topScores.value.size > 3){
            _topScores.value = _topScores.value.subList(0,3)
        }
    }

    fun addTry(){
        _tries.value += 1
    }
}