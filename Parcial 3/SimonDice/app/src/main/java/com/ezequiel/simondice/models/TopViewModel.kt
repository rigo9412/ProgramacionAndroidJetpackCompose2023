package com.ezequiel.simondice.modelo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezequiel.simondice.models.UiState
import com.ezequiel.simondice.domain.modelo.Player
import com.ezequiel.simondice.repositorio.SimonGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.logging.SocketHandler
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(val simonGameRepository: SimonGameRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<UiState>(UiState.Loading)
    var uiState: StateFlow<UiState> = _uiState

    private var _uiStateTheme = MutableStateFlow<Boolean>(false)
    var uiStateTheme: StateFlow<Boolean> = _uiStateTheme

    private var toCardGet = MutableStateFlow<Boolean>(false)
    var toCardGetState: StateFlow<Boolean> = toCardGet

    init {

        getTop()
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

    private fun listenNewTopPlayer(){
        viewModelScope.launch {
            simonGameRepository.listenNewTopPlayer().collect()  {
                getTop()
            }
        }
    }

    fun setTheme(darkTheme : Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            simonGameRepository.saveTheme(darkTheme)
        }
    }

    private fun getTheme(){
        viewModelScope.launch(Dispatchers.IO){
            simonGameRepository.getTheme().collect{
                _uiStateTheme.value = it
            }
        }
    }

}
