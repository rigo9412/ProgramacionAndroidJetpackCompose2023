package com.tec.pokedexapp.domain.repository

import android.util.Log
import com.google.gson.Gson
import com.tec.pokedexapp.data.model.ApiPostItem
import com.tec.pokedexapp.data.model.User
import com.tec.pokedexapp.domain.source.RestDataSource
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

interface UserRespository {
    suspend fun getUsers(): List<User>
    suspend fun postUser(user: User): User
}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
) : UserRespository{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    //NOTA, EL FORMATO DE FECHA A ENVIAR ES YMD en string
    override suspend fun getUsers(): List<User> {
        val result = mutableListOf<User>()
        val response =  dataSource.getResponse()
        response.map {
            result.add(User(it.id,it.name,it.country, it.startDate, it.finishDate,triesToFinish = it.triesToFinish,minutesToFinish = it.minutesToFisnish))
        }
        return result
    }

    override suspend fun postUser(user: User): User {
        val data = ApiPostItem(user.country,user.finishDate,user.minutesToFinish,user.name,user.startDate,user.triesToFinish)
        return dataSource.postTop(data)
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
