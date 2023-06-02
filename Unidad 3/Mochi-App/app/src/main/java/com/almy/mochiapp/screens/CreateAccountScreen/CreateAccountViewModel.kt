package com.almy.mochiapp.screens.CreateAccountScreen

import androidx.lifecycle.ViewModel
import com.almy.mochiapp.screens.LoginScreen.LoginUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CreateAccountViewModel : ViewModel() {
    private val _uiStateCreateAccount = MutableStateFlow<CreateAccountUiModel>(CreateAccountUiModel())
    val uiStateCreateAccount: StateFlow<CreateAccountUiModel> = _uiStateCreateAccount

}



data class CreateAccountUiModel(
    val name: String = "",
    val password: String = "",
    val fingerPrint: String = "",
    val isDetected: Boolean = false,
    val loginEnable: Boolean = false
)

sealed class CreateAccountUiState{
    class LoginScreen(): CreateAccountUiState()

    class CrearCuentaScreen(): CreateAccountUiState()
}