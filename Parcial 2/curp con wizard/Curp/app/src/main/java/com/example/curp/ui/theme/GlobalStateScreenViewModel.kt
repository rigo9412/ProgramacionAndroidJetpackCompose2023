package com.example.curp.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GlobalStateScreenViewModel: ViewModel() {
    private val _uiScreenState = MutableStateFlow<GlobalStateScreen>(GlobalStateScreen.HomeScreen)
    val uiScreenState: StateFlow<GlobalStateScreen> = _uiScreenState

    fun onEvent(event: GlobalStateScreen) {
        _uiScreenState.value = event
    }
}