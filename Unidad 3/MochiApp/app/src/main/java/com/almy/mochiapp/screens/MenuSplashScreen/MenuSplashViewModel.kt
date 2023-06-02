package com.almy.mochiapp.screens.MenuSplashScreen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.almy.mochiapp.firebase.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import kotlin.concurrent.timerTask


class MenuSplashViewModel: ViewModel(){
    private val _uiStateMenuSplash = MutableStateFlow<MenuSplashUiModel>( MenuSplashUiModel())
    val uiStateMenuSplash: StateFlow<MenuSplashUiModel> = _uiStateMenuSplash

     fun onMenuSplashLoading() {
        Timer().schedule(timerTask {
                _uiStateMenuSplash.value = _uiStateMenuSplash.value.copy(menuSplashTimeSpan = true)
             }, 3000)
    }

}
data class MenuSplashUiModel(
    val menuSplashTimeSpan: Boolean = false
)