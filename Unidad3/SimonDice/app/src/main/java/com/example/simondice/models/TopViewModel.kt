package com.example.simondice.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simondice.domain.models.Player
import com.example.simondice.repository.SimonGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(val simonGameRepository: SimonGameRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<UiState>(UiState.Loading)
    var uiState: StateFlow<UiState> = _uiState

    private val _notificationState =
        MutableStateFlow<NotificationState>(NotificationState.HideBotification)
    val notificationState: StateFlow<NotificationState> = _notificationState

    private var toCardGet = MutableStateFlow(false)
    var toCardGetState: StateFlow<Boolean> = toCardGet



    init {
        //getTop()
        listenNewTopPlayer()
    }

    fun getTop() =
        viewModelScope.launch {
            simonGameRepository.getTop().onStart {
                _uiState.value = UiState.Loading
            }.catch {
                _uiState.value = UiState.Error(it.message ?: "Error")
            }.collect {
                _uiState.value = UiState.Ready(it)
                toCardGet.value = true
            }
        }

    fun toLoading(){
        _uiState.value = UiState.Loading
    }

    fun toCardGetFalse(){
        toCardGet.value = false
    }
    fun toCardGetTrue(){
        toCardGet.value = true
    }
    fun postTop(player : Player){
        viewModelScope.launch {
            simonGameRepository.postTop(player).onStart {
                _uiState.value = UiState.Loading
            }.catch {
                _uiState.value = UiState.Error(it.message ?: "Error")
            }.collect {
                _uiState.value = UiState.Ready(it)
            }
        }
    }

    private fun listenNewTopPlayer() {
        viewModelScope.launch {
            simonGameRepository.listenNewTopPlayer().collect() {
                _notificationState.value = NotificationState.ShowNotification(it)
                Log.d(
                    "NewTopPlayer",
                    "Nuevo jugador añadido: ${it.name}, Nivel:  ${it.level}, Puntuación: ${it.score}"
                )
            }
        }
    }

}
