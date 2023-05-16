package com.lanazirot.simonsays.ui.screens.scoreboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.simonsays.domain.services.interfaces.IApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(private val apiService: IApiService) : ViewModel() {

    private val _scoreboardState = MutableStateFlow(ScoreboardState())
    val scoreboardState = _scoreboardState.asStateFlow()

    init {
        this.getScores()
    }

    private fun getScores() {
        viewModelScope.launch {
            apiService.getTopTenScores().onStart {
                _scoreboardState.value = ScoreboardState(isLoading = true)
            }.catch {
                _scoreboardState.value = ScoreboardState(error = it.message!!)
            }.collect {
                _scoreboardState.value = ScoreboardState(players = it)
            }
        }
    }
}