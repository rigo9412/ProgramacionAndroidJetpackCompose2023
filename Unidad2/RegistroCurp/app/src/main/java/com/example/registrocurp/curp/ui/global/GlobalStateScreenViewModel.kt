package com.example.registrocurp.curp.ui.global

import androidx.lifecycle.ViewModel
import com.example.registrocurp.curp.ui.global.GlobalStateScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GlobalStateScreenViewModel: ViewModel() {
    private val _uiScreenState = MutableStateFlow<GlobalStateScreen>(GlobalStateScreen.HomeScreen)
    val uiScreenState: StateFlow<GlobalStateScreen> = _uiScreenState

    fun onEvent(event: GlobalStateScreen) {
        _uiScreenState.value = event
    }
}