package com.otop.SimonDice.domain

import com.otop.SimonDice.domain.services.network.IApiService.Companion.BASE_URL
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException


object SocketHandler {

    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
// "http://10.0.2.2:3000" is the network your Android emulator must use to join the localhost network on your computer
// "http://localhost:3000/" will not work
// If you want to use your physical phone you could use your ip address plus :3000
// This will allow your Android Emulator and physical device at your home to connect to the server
            mSocket = IO.socket(BASE_URL)
        } catch (e: URISyntaxException) {
            println(e.message)
        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}