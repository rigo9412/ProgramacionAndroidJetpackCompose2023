package com.tec.pokedexapp.ui.navigator.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tec.pokedexapp.data.PokemonLocalRepository
import com.tec.pokedexapp.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PerfilViewModel(
    private val pokemonLocalRepository: PokemonLocalRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User>(User(-1))
    val user : StateFlow<User> = _user

    private val _finished = MutableStateFlow(false )
    val finished : StateFlow<Boolean> = _finished

    private var tryStartTime = LocalDateTime.now()
    private var tryFinishTime = LocalDateTime.now()

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    init{
        viewModelScope.launch {
            val dbUser = pokemonLocalRepository.getUsers().first()
            Log.d("DBUSER",dbUser.toString())
            if(dbUser.isNotEmpty()){
                _user.value = dbUser[0]
                _finished.value = _user.value.finishDate != ""

                Log.d("DBUSER","NOT EMPTY ${_user.value.toString()}")
            }
            else{
                pokemonLocalRepository.insertUser(_user.value)

                Log.d("DBUSER","EMPTY, INSERTED")
            }
        }
    }

    fun addScore(newScore : Int){
        when {
            newScore > _user.value.topScore1 -> {
                _user.value = _user.value.copy(topScore3 = _user.value.topScore2)
                _user.value = _user.value.copy(topScore2 = _user.value.topScore1)
                _user.value = _user.value.copy(topScore1 = newScore)
            }
            newScore > _user.value.topScore2-> {
                _user.value = _user.value.copy(topScore3 = _user.value.topScore2)
                _user.value = _user.value.copy(topScore2 = newScore)
            }
            newScore > _user.value.topScore3 -> {
                _user.value = _user.value.copy(topScore3 = newScore)
            }
        }
        updateDBUser(_user.value)
    }

    fun startTry(){
        tryStartTime = LocalDateTime.now()
    }

    fun finishTry(){
        tryFinishTime = LocalDateTime.now()
        addTry()
    }

    fun addTry(){
        if(_user.value.finishDate == "") {
            _user.value = _user.value.copy(triesToFinish = _user.value.triesToFinish + 1)
            addTime()
            updateDBUser(_user.value)
        }
    }

    fun setStartDate(){
        val currentTime = LocalDate.now()
        _user.value = _user.value.copy(startDate = currentTime.format(formatter))

        updateDBUser(_user.value)
    }

    fun setFinishDate(){
        val currentTime = LocalDate.now()
        _user.value = _user.value.copy(finishDate = currentTime.format(formatter))

        updateDBUser(_user.value)
    }

    fun addTime(){
        val seconds = Duration.between(tryStartTime,tryFinishTime).seconds.toInt()
        Log.d("Seconds",seconds.toString())
        _user.value = _user.value.copy(minutesToFinish = _user.value.minutesToFinish + seconds)
    }

    fun setName(name: String){
        _user.value = _user.value.copy(name = name)
    }

    fun setCountry(country: String){
        _user.value = _user.value.copy(country = country)
    }

    fun updateUser(){
        viewModelScope.launch {
            pokemonLocalRepository.updateUser(_user.value)
        }
    }

    fun finish(finished: Boolean){
        _finished.value = finished
    }
    private fun updateDBUser(user: User){
        viewModelScope.launch {
            pokemonLocalRepository.updateUser(user)
        }
    }

    fun reiniciarProgreso(){
        _user.value = User(-1)
        _finished.value = false
        updateDBUser(_user.value)
    }
}