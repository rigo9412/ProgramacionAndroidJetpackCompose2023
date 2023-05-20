package com.almy.simondice.domain.repository

import android.util.Log
import com.almy.simondice.domain.SocketHandler
import com.almy.simondice.domain.models.postrequest.Data
import com.almy.simondice.domain.models.postrequest.PostRequest
import com.almy.simondice.domain.models.Player
import com.almy.simondice.domain.service.network.IApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SimonGameRepository @Inject constructor(val apiService: IApiService, val moshi: Moshi, val db: PlayerDao) {

    private val _data: MutableStateFlow<List<Player>> = MutableStateFlow(db.getAll()/*listOf()*/)
    val data: StateFlow<List<Player>> = _data.asStateFlow()

    init {
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
    }


    fun getTop(): Flow<List<Player>> = flow {
        val result = mutableListOf<Player>()
        val response = apiService.getTop()
        response.data.map {
            result.add(Player(it.id, it.attributes?.name ?: "?????",it.attributes?.value ?: 0,1))
        }
        result.sortedByDescending { it.score }
        emit(result)
    }.flowOn(Dispatchers.IO)

    fun postTop(player: Player): Flow<List<Player>> = flow {
        val response = apiService.postTop(PostRequest(Data(level = player.level, name = player.name ?: "????", value = player.score)))
        val player = Player(
            id = response.data.id,
            name = player.name,
            score = player.score,
            level = player.level
        )
        val socket = SocketHandler.getSocket()
        socket.emit("newTop", playerToJson(player))
        emit(_data.value)
    }.flowOn(Dispatchers.IO)

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


    private fun playerToJson(player: Player): String{
        val jsonAdapter: JsonAdapter<Player> = moshi.adapter<Player>(Player::class.java)
        return  jsonAdapter.toJson(player)
    }

    fun getTheme() = simonStore.getThemeConfig
    suspend fun saveTheme(darkTheme: Boolean){
        simonStore.saveThemeConfig(darkTheme)
    }
    private fun jsonToPlayer(player: String): Player?{
        val jsonAdapter: JsonAdapter<Player> = moshi.adapter<Player>(Player::class.java)
        return  jsonAdapter.fromJson(player)
    }
}