package com.tec.pokedexapp.ui.navigator.main.leaderboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tec.pokedexapp.data.model.User
import com.tec.pokedexapp.domain.repository.UserRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val userRepositoryImp: UserRespository
) : ViewModel() {

    private val _top10 = MutableStateFlow<List<User>>(emptyList())
    val top10 : StateFlow<List<User>> = _top10

    private val _loaded = MutableStateFlow(false)
    val loaded : StateFlow<Boolean> = _loaded

    fun updateTop10(){
        _loaded.value = false
        viewModelScope.launch(Dispatchers.IO) {
            _top10.value = userRepositoryImp.getTop10()
            _loaded.value = true
        }
    }

    fun postUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepositoryImp.postUser(user)
        }
    }
}
