package com.ezequiel.simondice.repositorio

import android.util.Log
import com.ezequiel.simondice.domain.SimonStore
import com.ezequiel.simondice.domain.SocketHandler
import com.ezequiel.simondice.domain.dao.PlayerDao
import com.ezequiel.simondice.domain.modelo.Player
import com.ezequiel.simondice.domain.service.network.IApiService
import com.ezequiel.simondice.domain.modelo.postTop.Data
import com.ezequiel.simondice.domain.modelo.postTop.PostRequestTop
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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimonGameRepository
@Inject constructor(val apiService: IApiService, val moshi: Moshi, val db : PlayerDao, val store : SimonStore) {
    private val _data: MutableStateFlow<List<Player>> = MutableStateFlow(db.getAll())
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
                    db.insert(player)
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
        db.insertAll(result)
        emit(result)
    }.flowOn(Dispatchers.IO)

    fun postTop(player: Player): Flow<List<Player>> = flow {
        val response = apiService.postTop(PostRequestTop(Data(level = player.level,name = player.name, value = player.score)))
        val player = Player(
            id= response.data.id,
            name = player.name,
            score = player.score,
            level = player.level,
        )
        db.insert(player)

        //_tops.value.add(player)
        val socket = SocketHandler.getSocket()
        socket.emit("newTop",playerToJson(player))


        emit(_data.value)
    }.flowOn(Dispatchers.IO)

    private fun playerToJson(player: Player): String{
        val jsonAdapter: JsonAdapter<Player> = moshi.adapter<Player>(Player::class.java)
        return  jsonAdapter.toJson(player)
    }


    private fun jsonToPlayer(player: String): Player?{
        val jsonAdapter: JsonAdapter<Player> = moshi.adapter<Player>(Player::class.java)
        return  jsonAdapter.fromJson(player)
    }


    fun getTheme() = store.getThemeConfig

    suspend fun saveTheme(darkTheme : Boolean){
        store.saveThemeConfig(darkTheme)
    }
}
