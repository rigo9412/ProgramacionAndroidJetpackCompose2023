package com.example.myapplication.ui.gato.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GatoGameViewModel : ViewModel(){
    private val _currentPlayer = MutableLiveData<String>()
    val currentPlayer : LiveData<String> = _currentPlayer
    private val _winner = MutableLiveData<String>()
    val winner : LiveData<String> = _winner

    private val _playing = MutableLiveData<Boolean>()
    val playing : LiveData<Boolean> = _playing
    private val _ended = MutableLiveData<Boolean>()
    val ended : LiveData<Boolean> = _ended

    var player1 = "Wario"
    var player2 = "Luigi"

    private val _isSelected1 = MutableLiveData<String>()
    val isSelected1 : LiveData<String> = _isSelected1
    private val _isSelected2 = MutableLiveData<String>()
    val isSelected2 : LiveData<String> = _isSelected2
    private val _isSelected3 = MutableLiveData<String>()
    val isSelected3 : LiveData<String> = _isSelected3
    private val _isSelected4 = MutableLiveData<String>()
    val isSelected4 : LiveData<String> = _isSelected4
    private val _isSelected5 = MutableLiveData<String>()
    val isSelected5 : LiveData<String> = _isSelected5
    private val _isSelected6 = MutableLiveData<String>()
    val isSelected6 : LiveData<String> = _isSelected6
    private val _isSelected7 = MutableLiveData<String>()
    val isSelected7 : LiveData<String> = _isSelected7
    private val _isSelected8 = MutableLiveData<String>()
    val isSelected8 : LiveData<String> = _isSelected8
    private val _isSelected9 = MutableLiveData<String>()
    val isSelected9 : LiveData<String> = _isSelected9

    var gridState = mutableListOf<String>("","","","","","","","","")

    fun gameStart(){
        _currentPlayer.value = player1
        _playing.value = true
        _ended.value = false
    }

    fun onSelectChange(num : Int) {
        gridState[num-1] = if (_currentPlayer.value == "Wario") "X" else "O"
        when(num){
            1 -> _isSelected1.value = if (_currentPlayer.value == "Wario") "X" else "O"
            2 -> _isSelected2.value = if (_currentPlayer.value == "Wario") "X" else "O"
            3 -> _isSelected3.value = if (_currentPlayer.value == "Wario") "X" else "O"
            4 -> _isSelected4.value = if (_currentPlayer.value == "Wario") "X" else "O"
            5 -> _isSelected5.value = if (_currentPlayer.value == "Wario") "X" else "O"
            6 -> _isSelected6.value = if (_currentPlayer.value == "Wario") "X" else "O"
            7 -> _isSelected7.value = if (_currentPlayer.value == "Wario") "X" else "O"
            8 -> _isSelected8.value = if (_currentPlayer.value == "Wario") "X" else "O"
            9 -> _isSelected9.value = if (_currentPlayer.value == "Wario") "X" else "O"
        }
        checkGameOver()
    }

    fun checkGameOver(){
        // Check for horizontal wins
        for (i in 0..6 step 3) {
            if (gridState[i].isNotBlank() && gridState[i] == gridState[i+1] && gridState[i] == gridState[i+2]) {
                gameOver(gridState[i])
            }
        }

        // Check for vertical wins
        for (i in 0..2) {
            if (gridState[i].isNotBlank() && gridState[i] == gridState[i+3] && gridState[i] == gridState[i+6]) {
                gameOver(gridState[i])
            }
        }

        // Check for diagonal wins
        if (gridState[0].isNotBlank() && gridState[0] == gridState[4] && gridState[0] == gridState[8]) {
            gameOver(gridState[0])
        }
        if (gridState[2].isNotBlank() && gridState[2] == gridState[4] && gridState[2] == gridState[6]) {
            gameOver(gridState[2])
        }

        // Check for tie
        if (!gridState.any { it.isBlank() }) {
            gameOver("")
        }
        Log.d("CURRENT PLAYER",_currentPlayer.value.toString())
        if(_currentPlayer.value == "Wario"){
            _currentPlayer.value = "Luigi"
        }
        else{
            _currentPlayer.value = "Wario"
        }
    }

    fun gameOver(text : String){
        _winner.value = if(text == "X") "Wario" else (if(text == "O") "Luigi" else "Empate")
        _ended.value = true
        _playing.value = false
    }

    fun resetGame(){
        _isSelected1.value = ""
        _isSelected2.value = ""
        _isSelected3.value = ""
        _isSelected4.value = ""
        _isSelected5.value = ""
        _isSelected6.value = ""
        _isSelected7.value = ""
        _isSelected8.value = ""
        _isSelected9.value = ""

        _currentPlayer.value = ""
        _ended.value = false
        _winner.value = ""
        _playing.value = false
        gridState = mutableListOf<String>("","","","","","","","","")

    }

}