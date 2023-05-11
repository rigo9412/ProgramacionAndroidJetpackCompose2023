package com.example.simondicef.domain.repository

import android.util.Log
import com.example.simondicef.domain.SocketHandler
import com.example.simondicef.domain.models.PostResponse
import com.example.simondicef.domain.models.User
import com.example.simondicef.domain.models.top.Data
import com.example.simondicef.domain.models.top.postData
import com.example.simondicef.domain.source.RestDataSource
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface UserRespository {
    val data: StateFlow<List<User>>
    suspend fun getUsers(): List<User>
    suspend fun postUser(user: User): User
    fun listenNewTop(): Flow<User>
}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
) : UserRespository{

    private val _data: MutableStateFlow<List<User>> = MutableStateFlow(listOf())
    override val data: StateFlow<List<User>> = _data.asStateFlow()

    init {
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
    }

    override suspend fun getUsers(): List<User> {
        val result = mutableListOf<User>()
        val response =  dataSource.getResponse()
        response.data.map {
            result.add(User(it.attributes.name,it.attributes.value,it.id))
        }
        return result
    }

    override suspend fun postUser(user: User): User {
        val data = postData(Data(12,user.name,user.score))
        val response = dataSource.postTop(data)
        val msocket = SocketHandler.getSocket()
        var userResponse = user
        userResponse.id = response.data.id
        msocket.emit("newTop",userToJSON(userResponse))
        return User(response.data.attributes.name, response.data.attributes.value, response.data.id)
    }

    override fun listenNewTop(): Flow<User> = channelFlow{
        val socket = SocketHandler.getSocket()

        socket.on("notification.top"){
            Log.d("SOCKET",it[0].toString())
            val user = JSONtoUser(it[0].toString())

            if(user != null){
                val found = data.value.find { u -> u.id == user.id  }
                if(found == null){
                    val mutableList = mutableListOf<User>()
                    mutableList.add(user)
                    mutableList.sortByDescending { it.score }

                    _data.value = mutableList
                    trySend(user)
                }
            }
        }
        awaitClose()
    }

    fun userToJSON(user: User): String{
        val json = Gson().toJson(user)
        Log.d("JSON",json)
        return json
    }

    fun JSONtoUser(string: String): User{
        return Gson().fromJson(string,User::class.java)
    }

}