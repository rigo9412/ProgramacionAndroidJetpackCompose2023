package com.almy.mochiapp.screens.Account

import androidx.lifecycle.ViewModel
import com.almy.mochiapp.screens.CreateTask.AssigmentData
import com.almy.mochiapp.screens.CreateTask.ScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AccountViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<ScreenUiState>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState> = _uiState

    private val _uiStateData = MutableStateFlow<AccountData>(AccountData())
    val uiStateData: StateFlow<AccountData> = _uiStateData


}

data class AccountData(
    var userName: String = "",
    var listFriends: List<String> = listOf(),
    var email: String = "",
    var totalAssigments: Int = 0,
    var assigmentsCompleted: Int = 0,
    var pendingAssigments: Int = 0
)
