package com.example.simondicef.leaderboard.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simondicef.R
import com.example.simondicef.domain.models.User
import com.example.simondicef.domain.repository.UserRespository
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class NotificationState (
    val user: User = User(),
    val show: Boolean = false

)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepositoryImp: UserRespository
): ViewModel() {

    val data: Flow<List<User>> = userRepositoryImp.data

    private val _notificationState =
        MutableStateFlow<NotificationState>(NotificationState())
    val notificationState: StateFlow<NotificationState> = _notificationState

    init{
        listeNewTop()
    }

    suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        userRepositoryImp.getUsers()
    }

    suspend fun postUser(user: User): User = withContext(Dispatchers.IO){
        userRepositoryImp.postUser(user)
    }

    private fun listeNewTop(){
        viewModelScope.launch {
            userRepositoryImp.listenNewTop().collect(){
                _notificationState.value = _notificationState.value.copy(user = it,show = true)

            }
        }
    }

    fun hideNotif(){
        _notificationState.value = NotificationState()
    }
}