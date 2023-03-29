package com.otop.chinpokomon.data
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MainViewModel: ViewModel() {
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
    val currentScreen: StateFlow<Screen> = _currentScreen


    fun navigateToHome() {
        _currentScreen.value = Screen.Home
    }

    fun navigateToProfile() {
        _currentScreen.value = Screen.Profile
    }

    fun navigateToSettings() {
        _currentScreen.value = Screen.Settings
    }
}