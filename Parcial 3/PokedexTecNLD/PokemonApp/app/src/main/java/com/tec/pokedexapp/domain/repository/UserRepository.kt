package com.tec.pokedexapp.domain.repository

import android.util.Log
import com.google.gson.Gson
import com.tec.pokedexapp.data.model.ApiPostItem
import com.tec.pokedexapp.data.model.User
import com.tec.pokedexapp.domain.source.RestDataSource
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.ceil

interface UserRespository {
    suspend fun getUsers(): List<User>
    suspend fun getTop10(): List<User>
    suspend fun postUser(user: User)
}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
) : UserRespository{

    //NOTA, EL FORMATO DE FECHA A ENVIAR ES YMD en string
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override suspend fun getUsers(): List<User> {
        val result = mutableListOf<User>()
        val response =  dataSource.getResponse()
        response.map {
            result.add(User(it.id,it.name,it.country, it.startDate, it.finishDate,triesToFinish = it.triesToFinish,minutesToFinish = it.minutesToFinish))
        }
        return result
    }

    override suspend fun getTop10(): List<User> {
        val result = mutableListOf<User>()
        val response =  dataSource.getTop10()
        response.map {
            result.add(User(it.id,it.name,it.country, it.startDate, it.finishDate,triesToFinish = it.triesToFinish,minutesToFinish = it.minutesToFinish))
        }
        return result
    }

    override suspend fun postUser(user: User){
        val data = ApiPostItem(user.country,user.finishDate,ceil(user.minutesToFinish.toDouble()/60).toInt(),user.name,user.startDate,user.triesToFinish)
        dataSource.postTop(data)
    }
    fun userToJSON(user: User): String{
        val json = Gson().toJson(user)
        return json
    }

    fun JSONtoUser(string: String): User{
        return Gson().fromJson(string,User::class.java)
    }
}
