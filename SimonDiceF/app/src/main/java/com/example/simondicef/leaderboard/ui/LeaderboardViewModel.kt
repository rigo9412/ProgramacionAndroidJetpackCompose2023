package com.example.simondicef.leaderboard.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LeaderboardViewModel: ViewModel() {
//    private val _uiStateData = MutableStateFlow<CurpFormModelState>(CurpFormModelState())
//    val uiStateData: StateFlow<CurpFormModelState> = _uiStateData

    private val _top10 = MutableStateFlow(mutableListOf<Pair<String, Int>>())
    val top10: StateFlow<List<Pair<String, Int>>> = _top10

    private val _entry = MutableStateFlow<Boolean>(true)
    val entry: StateFlow<Boolean> = _entry

    private val _return = MutableStateFlow(true)
    val returnB : StateFlow<Boolean> = _return

    private val _justShow = MutableStateFlow(false)
    val justShow : StateFlow<Boolean> = _justShow

    fun addScore(name: String, score: Int){
        var x = Pair(name,score)
        _top10.value.add(Pair(name,score))
        _top10.value.sortByDescending { it.second }
        while (_top10.value.size > 10) {
            _top10.value.removeLast()
        }
        _entry.value = false
    }

    fun checkIfTop(score: Int): Boolean{
        if(_justShow.value){
            _entry.value = false
            return false
        }

        if(_top10.value.size < 10){
            return true
        }
        for (entry in _top10.value){
            if(score > entry.second){
                return true
            }
        }
        _entry.value = false
        return false
    }

    fun goBack(bool : Boolean){
        _return.value = bool
        _entry.value = true
    }
    fun changeEntry(bool : Boolean){
        _entry.value = bool
    }

    fun changeJustShow(bool : Boolean){
        _justShow.value = bool
        _entry.value = !bool
    }

}