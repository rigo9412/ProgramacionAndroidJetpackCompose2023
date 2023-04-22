package com.ezequiel.simondice19100155.models

import android.util.Range
import com.ezequiel.simondice.models.Player

class Top10 {
    private val _list = mutableListOf<Player>()

    val list get() = _list

    fun add(player:Player){
        _list.add(player)
        _list.sortByDescending { it.Puntos}

    }

    fun clear(){
        _list.clear()
    }

    fun only10(){
        if (!_list.isEmpty()){
        _list.sortByDescending { it.Puntos }
        if(_list.size > 10){
            _list.removeAt(10)
        }
        }
    }
    fun eliminarPara10(){
        if (!_list.isEmpty() && _list.size > 10){
        for (jug : Player in _list){
            only10()
        }}
    }
}