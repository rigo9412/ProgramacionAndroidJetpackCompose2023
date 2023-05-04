package com.otop.SimonDice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otop.SimonDice.domain.Repository.SimonGameRepository
import com.otop.SimonDice.domain.models.Player
import com.otop.SimonDice.ui.top.NotificationState
import com.otop.SimonDice.ui.top.TopScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopViewModel @Inject constructor(val simonGameRepository: SimonGameRepository): ViewModel() {
    val data: Flow<List<Player>> = simonGameRepository.data
    val dataPlayer: StateFlow<List<Player>> = simonGameRepository.data

    private val _notificationState =
        MutableStateFlow<NotificationState>(NotificationState.HideBotification)
    val notificationState: StateFlow<NotificationState> = _notificationState

    private val _uiState =
        MutableStateFlow<TopScreenState>(TopScreenState.Loading)
    val uiState: StateFlow<TopScreenState> = _uiState


    init {
        getTop()
        listenNewTopPlayer()
        listerTops()
    }

    private fun listerTops(){
        viewModelScope.launch {
            simonGameRepository.data.collect {
                _uiState.value = TopScreenState.Ready(it)
            }
        }
    }

    private fun listenNewTopPlayer() {
        viewModelScope.launch {
            simonGameRepository.listenNewTopPlayer()
                .collect {
                    _notificationState.value = NotificationState.ShowNotification(it)
                }
        }
    }



    fun getTop() =
        viewModelScope.launch {
            simonGameRepository.getTop().onStart {
                _uiState.value= TopScreenState.Loading
            }.catch {
                _uiState.value= TopScreenState.Error(it.message ?: "ERROR FATAl")
            }.collect {
                _uiState.value=TopScreenState.Ready(it)
            }
        }

    fun postTopFake(player: Player) =
        viewModelScope.launch {
            simonGameRepository.postTop(player).onStart {
                _uiState.value= TopScreenState.Loading
            }.catch {
                _uiState.value= TopScreenState.Error(it.message ?: "ERROR FATAl")
            }.collect {
                _uiState.value=TopScreenState.Ready(it)
            }
        }
}