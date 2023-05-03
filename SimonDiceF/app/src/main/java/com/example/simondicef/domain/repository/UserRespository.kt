package com.example.simondicef.domain.repository

import android.util.Log
import com.example.simondicef.domain.models.PostResponse
import com.example.simondicef.domain.models.User
import com.example.simondicef.domain.models.top.Data
import com.example.simondicef.domain.models.top.postData
import com.example.simondicef.domain.source.RestDataSource
import com.google.gson.Gson
import javax.inject.Inject

interface UserRespository {
    suspend fun getUsers(): List<User>
    suspend fun postUser(user: User): User
}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
) : UserRespository{


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
        return User(response.data.attributes.name, response.data.attributes.value, response.data.id)
    }

    fun userToJSON(user: User): String{
        val data = postData(Data(12,user.name,user.score))
        val json = Gson().toJson(data)
        Log.d("JSON",json)
        print(json)
        return json
    }

}