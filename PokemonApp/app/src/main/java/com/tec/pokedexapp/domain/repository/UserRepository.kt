package com.tec.pokedexapp.domain.repository

import com.tec.pokedexapp.data.model.User
import com.tec.pokedexapp.domain.source.RestDataSource
import javax.inject.Inject

interface UserRespository {
    suspend fun getUsers(): List<User>
    suspend fun postUser(user: User): User
}

//class UserRepositoryImp @Inject constructor(
//    private val dataSource: RestDataSource,
//) : UserRespository{

//    override suspend fun getUsers(): List<User> {
//        val result = mutableListOf<User>()
//        val response =  dataSource.getResponse()
//        response.data.map {
//            result.add(User(it.attributes.name,it.attributes.value,it.id))
//        }
//        return result
//    }
//
//    override suspend fun postUser(user: User): User {
//        val data = postData(Data(12,user.name,user.score))
//        val response = dataSource.postTop(data)
//        val msocket = SocketHandler.getSocket()
//        var userResponse = user
//        userResponse.id = response.data.id
//        return User(response.data.attributes.name, response.data.attributes.value, response.data.id)
//    }
//    fun userToJSON(user: User): String{
//        val json = Gson().toJson(user)
//        Log.d("JSON",json)
//        return json
//    }
//
//    fun JSONtoUser(string: String): User{
//        return Gson().fromJson(string,User::class.java)
//    }

//}