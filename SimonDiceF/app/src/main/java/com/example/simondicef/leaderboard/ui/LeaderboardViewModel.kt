package com.example.simondicef.leaderboard.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simondicef.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class LeaderboardViewModel(
): ViewModel() {
    private val _top10 = MutableStateFlow(mutableListOf<User>())
    val top10: StateFlow<List<User>> = _top10

    private val _entry = MutableStateFlow<Boolean>(true)
    val entry: StateFlow<Boolean> = _entry

    private val _return = MutableStateFlow(true)
    val returnB : StateFlow<Boolean> = _return

    private val _justShow = MutableStateFlow(false)
    val justShow : StateFlow<Boolean> = _justShow

    fun addScore(name: String, score: Int){
        var x = Pair(name,score)
        val existingUserIndex = top10.value.indexOfFirst { it.name == name }
        if (existingUserIndex >= 0 && top10.value[existingUserIndex].score >= score) {
            // Name already exists in the list with a higher or equal score, return from function
            return
        }

        _top10.value.add(User(name = name, score = score))
        _top10.value.sortByDescending { it.score }
        while (_top10.value.size > 10) {
            _top10.value.removeLast()
        }
        _entry.value = false
    }

    fun getLeaderBoard(users: List<User>){
        for (user in users){
            addScore(user.name,user.score)
        }
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
            if(score > entry.score!!){
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

//    fun getUsers(){
//        viewModelScope.launch(Dispatchers.IO) {
//            val users = simonDiceRepository.getResponse()
//            Log.d("REPOSITORY",users.toString())
//        }
//    }

}