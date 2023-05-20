package com.almy.simondice.ui.topstate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almy.simondice.domain.models.Player
import com.almy.simondice.domain.repository.SimonGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val _uiStateTheme = MutableStateFlow<Boolean>(false)
    val uiStateTheme: StateFlow<Boolean> = _uiStateTheme


    val notificationState: StateFlow<NotificationState> = _notificationState


    init {
        getTop()
        listenNewTopPlayer()
        //listerTops()
    }
    fun getTop() =
        viewModelScope.launch {
            simonGameRepository.getTop().onStart {
                _uiState.value= TopScreenState.Loading
            }.catch {
                _uiState.value= TopScreenState.Error(it.message ?: "ERROR FATAl")
            }.collect {
                _uiState.value= TopScreenState.Ready(it)
            }
        }

    fun postTopFake(player: Player) =
        viewModelScope.launch {
            simonGameRepository.postTop(player).onStart {
                _uiState.value= TopScreenState.Loading
            }.catch {
                _uiState.value= TopScreenState.Error(it.message ?: "ERROR FATAl")
            }.collect {
                _uiState.value= TopScreenState.Ready(it)
            }
        }

    private fun listerTops(){
        viewModelScope.launch {
            simonGameRepository.data.collect {
                _uiState.value = TopScreenState.Ready(it)
            }
        }
    }
fun setTheme(darkTheme:Boolean){
    viewModelScope.launch(Dispatchers.IO){
        simonGameRepository.saveTheme(darkTheme)
    }
}
    private fun getTheme()
    {
        viewModelScope.launch (Dispatchers.IO){
            simonGameRepository.getTheme().collect {
                _uiStateTheme.value = it
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
}