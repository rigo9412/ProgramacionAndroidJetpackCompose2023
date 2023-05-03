package com.example.simondicef.leaderboard.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simondicef.domain.models.User
import com.example.simondicef.domain.repository.UserRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepositoryImp: UserRespository
): ViewModel() {

    suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        userRepositoryImp.getUsers()
    }

    suspend fun postUser(user: User): User = withContext(Dispatchers.IO){
        userRepositoryImp.postUser(user)
    }
}