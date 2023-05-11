package com.lanazirot.simonsays.domain.services.implementation

import com.lanazirot.simonsays.domain.repository.interfaces.IApiRepository
import io.socket.client.IO;
import io.socket.client.Socket;

class CustomSocketHandler {

    private val socket: Socket = IO.socket(IApiRepository.URL_SOCKET)

    init {
        try{
            socket.connect()
        }catch (e: Exception){
            println(e)
        }
    }

    companion object {
        private var instance: CustomSocketHandler? = null

        fun getInstance(): CustomSocketHandler {
            if (instance == null) {
                instance = CustomSocketHandler()
            }
            return instance!!
        }
    }

    fun emit(event: String, vararg args: Any) {
        socket.emit(event, *args)
    }

    fun on(event: String, listener: (Any) -> Unit) {
        socket.on(event, listener)
    }

    fun off(event: String) {
        socket.off(event)
    }

    fun disconnect() {
        socket.disconnect()
    }

    fun isConnected(): Boolean {
        return socket.connected()
    }

    fun connect() {
        socket.connect()
    }

    fun getSocket(): Socket {
        return socket
    }


}