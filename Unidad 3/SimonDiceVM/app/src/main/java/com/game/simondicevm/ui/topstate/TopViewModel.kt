package com.game.simondicevm.ui.topstate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rigo.simondice.domain.models.Player
import com.rigo.simondice.domain.repository.SimonGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopViewModel @Inject constructor(val simonGameRepository: SimonGameRepository): ViewModel() {

    val data: Flow<List<Player>> = simonGameRepository.data

    private val _uiState =
        MutableStateFlow<TopScreenState>(TopScreenState.Loading)
    val uiState: StateFlow<TopScreenState> = _uiState


    private val _notificationState =
        MutableStateFlow<NotificationState>(NotificationState.HideBotification)
    val notificationState: StateFlow<NotificationState> = _notificationState


    init {
        getTop()
        listenNewTopPlayer()
        listerTops()
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
                .catch{
                    _uiState.value= TopScreenState.Error(it.message ?: "Error al intentar escuchar nuevo post :/")
                }.collect {
                    _notificationState.value = NotificationState.ShowNotification(it)
                }
        }
    }
}