package com.rigo.simondice.ui.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rigo.simondice.domain.repository.SimonGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopViewModel @Inject constructor(val simonGameRepository: SimonGameRepository): ViewModel() {

    private val _uiState =
        MutableStateFlow<TopScreenState>(TopScreenState.Loading)
    val uiState: StateFlow<TopScreenState> = _uiState


    init {
        getTop()
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
}