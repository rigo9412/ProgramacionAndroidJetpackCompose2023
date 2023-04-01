package com.tec.pokedexapp.ui.navigator.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel : ViewModel() {
    private val _topScores = MutableStateFlow<List<Int>>(listOf())
    val topScores: StateFlow<List<Int>> = _topScores

    fun addScore(score : Int){
        _topScores.value = _topScores.value + score
        _topScores.value.sortedDescending()
        if(_topScores.value.size > 3){
            _topScores.value = _topScores.value.subList(0,3)
        }
    }
}