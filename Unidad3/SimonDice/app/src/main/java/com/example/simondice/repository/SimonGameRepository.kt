package com.example.simondice.repository

import android.util.Log
import com.example.simondice.domain.SocketHandler
import com.example.simondice.domain.models.Player
import com.example.simondice.domain.service.network.IApiService
import com.example.simondice.domain.models.postrequesttop.Data
import com.example.simondice.domain.models.postrequesttop.PostRequestTop
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimonGameRepository
@Inject constructor(val apiService: IApiService, val moshi: Moshi) {
    private val _data: MutableStateFlow<List<Player>> = MutableStateFlow(listOf())
    val data: StateFlow<List<Player>> = _data.asStateFlow()

    init {
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
    }

    fun listenNewTopPlayer(): Flow<Player> = channelFlow {
        val socket = SocketHandler.getSocket()
        socket.on("notification.top"
        ) { it ->
            Log.d("SOCKET", it[0].toString())
            val player = jsonToPlayer( it[0].toString())
            if(player != null){
                val find = data.value.find { p -> p.id == player.id }
                if(find == null){
                    val mutableList = mutableListOf<Player>()
                    mutableList.addAll(data.value)
                    mutableList.add(player)
                    mutableList.sortByDescending { it.score }
                    _data.value = mutableList
                    trySend(player)
                }
            }
        }
        awaitClose()
    }


    fun getTop(): Flow<List<Player>> = flow {
        val result = mutableListOf<Player>()
        val response = apiService.getTop()
        response.data.map {
            result.add(Player(null,it.attributes?.name ?: "?????",it.attributes?.value ?: 0,1))
        }
        result.sortByDescending { it.score }
        _data.value = result
        emit(result.take(10))
    }.flowOn(Dispatchers.IO)

    fun postTop(player: Player): Flow<List<Player>> = flow {
        val response = apiService.postTop(PostRequestTop(Data(level = player.level,name = player.name, value = player.score)))
        val player = Player(
            id= response.data.id,
            name = player.name,
            score = player.score,
            level = player.level,
        )
        val socket = SocketHandler.getSocket()
        socket.emit("newTop",playerToJson(player))
        emit(_data.value)
    }.flowOn(Dispatchers.IO)

    private fun playerToJson(player: Player): String{
        val jsonAdapter: JsonAdapter<Player> = moshi.adapter(Player::class.java)
        return  jsonAdapter.toJson(player)
    }

    private fun jsonToPlayer(player: String): Player?{
        val jsonAdapter: JsonAdapter<Player> = moshi.adapter<Player>(Player::class.java)
        return  jsonAdapter.fromJson(player)
    }
}
