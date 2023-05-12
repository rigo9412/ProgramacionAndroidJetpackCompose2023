package com.game.simondicevm.domain

import com.rigo.simondice.domain.service.network.IApiService
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler{

    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
            mSocket = IO.socket(IApiService.BASE_URL)
        } catch (e: URISyntaxException) {
            println(e.message)
        }
    }

    @Synchronized
    fun establishConnection(){
        mSocket.connect()
    }

    @Synchronized
    fun getSocket(): Socket{
        return mSocket
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}