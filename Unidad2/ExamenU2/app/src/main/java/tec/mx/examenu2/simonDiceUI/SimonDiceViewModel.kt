package tec.mx.examenu2.simonDiceUI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlin.random.Random

class SimonDiceViewModel : ViewModel(){

    val _highScores = mutableListOf<Score>()

    fun addScore(name: String, score: Int) {
        val newScore = Score(name, score)
        _highScores.add(newScore)
        _highScores.sortByDescending { it.score }
        if (_highScores.size > 10) {
            _highScores.removeAt(_highScores.lastIndex)
        }
    }

    fun addScoreWithUserName(name: String, score: Int) {
        addScore(name, score)
    }

    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int> = _score
    private val _started = MutableLiveData<Boolean>()
    val started : LiveData<Boolean> = _started
    private val _isInputEnabled = MutableLiveData<Boolean>()
    val isInputEnabled  : LiveData<Boolean> = _isInputEnabled
    private val _nivel = MutableLiveData<Int>()
    val nivel : LiveData<Int> = _nivel

    var difficulty = 1
    var lost = false
    var count = 0
    var scoreCounter = 0

    private val _tiempoRestante =  MutableLiveData<Long>()
    val tiempoRestante : LiveData<Long> = _tiempoRestante

    var selectionSequence : List<Int> = listOf()
    var selectionSequenceCounter = 0
    var selectedSequence : MutableList<Int> = mutableListOf()
    var currentSelected = 0

    private val _isSelected1 = MutableLiveData<Boolean>()
    val isSelected1 : LiveData<Boolean> = _isSelected1
    private val _isSelected2 = MutableLiveData<Boolean>()
    val isSelected2 : LiveData<Boolean> = _isSelected2
    private val _isSelected3 = MutableLiveData<Boolean>()
    val isSelected3 : LiveData<Boolean> = _isSelected3
    private val _isSelected4 = MutableLiveData<Boolean>()
    val isSelected4 : LiveData<Boolean> = _isSelected4
    var name : String = ""

    suspend fun empezarJuego(nombre : String){
        _started.value = true
        name = nombre
        empezarTurno()
    }

    suspend fun onSelectChange(num : Int) {
        selectedSequence.add(num)
        checkSelected()
    }

    suspend fun selectShow(num : Int){
        when(num){
            1 -> {_isSelected1.value = true; delay(500); _isSelected1.value = false;}
            2 -> {_isSelected2.value = true; delay(500); _isSelected2.value = false;}
            3 -> {_isSelected3.value = true; delay(500); _isSelected3.value = false;}
            4 -> {_isSelected4.value = true; delay(500); _isSelected4.value = false;}
        }
    }

    suspend fun empezarTurno(){
        if(!lost) {
            selectionSequence = List(difficulty) { Random.nextInt(1, 5) }
            selectionSequence += 1
            showSelection()
        }
    }

    suspend fun showSelection(){
        _isInputEnabled.value = false
        delay(timeMillis = 1250)
        for(s in selectionSequence){
            when(s){
                1 -> {_isSelected1.value = true; delay(750); _isSelected1.value = false; delay(300)}
                2 -> {_isSelected2.value = true; delay(750); _isSelected2.value = false; delay(300)}
                3 -> {_isSelected3.value = true; delay(750); _isSelected3.value = false; delay(300)}
                4 -> {_isSelected4.value = true; delay(750); _isSelected4.value = false; delay(300)}
            }
        }
        _isInputEnabled.value = true
    }

    suspend fun checkSelected(){
        Log.d("SELECTED","$selectedSequence")
        Log.d("SELECTION","$selectionSequence")
        var counter = 0
        if(selectionSequence.size == selectedSequence.size) {
            for (i in selectedSequence) {
                if (i != selectionSequence[counter]) {
                    addScoreWithUserName(name,scoreCounter)
                    ResetGame()
                    return
                }
                counter += 1
            }
            if(count == 3) {
                _nivel.value = difficulty
                difficulty += 1
                count = 0
            }
            else {
                count += 1
            }
            scoreCounter += 1
            _score.value = scoreCounter
            selectionSequence = listOf()
            selectedSequence = mutableListOf()
            empezarTurno()
        }
        if(selectedSequence.size > selectionSequence.size){
            selectedSequence = mutableListOf()
        }
    }

    private fun ResetGame(){
        _started.value = false
        _isInputEnabled.value = false
        selectedSequence = mutableListOf()
        selectionSequence = listOf()
        _nivel.value = 0
        scoreCounter = 0
        difficulty = 0
        _score.value = 0
        _isSelected1.value = false
        _isSelected2.value = false
        _isSelected3.value = false
        _isSelected4.value = false
    }

}