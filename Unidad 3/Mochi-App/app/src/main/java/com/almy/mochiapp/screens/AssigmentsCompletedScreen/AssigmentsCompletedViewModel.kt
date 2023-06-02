package com.almy.mochiapp.screens.AssigmentsCompletedScreen

import androidx.lifecycle.ViewModel
import com.almy.mochiapp.screens.LoginScreen.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AssigmentsCompletedViewModel : ViewModel() {
    private val _uiStateAssigmentsCompleted = MutableStateFlow<AssigmentsCompletedUiModel>(AssigmentsCompletedUiModel())
    val uiStateAssigmentsCompleted: StateFlow<AssigmentsCompletedUiModel> = _uiStateAssigmentsCompleted


    private val _AssigmentsCompletedUiState = MutableStateFlow<AssigmentsCompletedUiState>(
        AssigmentsCompletedUiState.AssigmentsCompletedScreen()
    )
    val assigmentsCompletedUiState: StateFlow<AssigmentsCompletedUiState> = _AssigmentsCompletedUiState


}



data class AssigmentsCompletedUiModel(
    val assigmentTitle: String = "",
    val isGroupAssigment: Boolean = false
)

sealed class AssigmentsCompletedUiState{
    class AssigmentsCompletedScreen(): AssigmentsCompletedUiState()
}