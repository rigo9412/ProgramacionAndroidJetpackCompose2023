package com.lanazirot.simonsays.domain.model.api.post

import com.google.gson.Gson


data class DataPost(
    val level: Int,
    val name: String,
    val value: Int,
    var id: Int? = null
){
    fun toJSON(): String = Gson().toJson(this)
}