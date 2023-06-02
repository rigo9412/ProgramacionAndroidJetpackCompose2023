package com.almy.mochiapp.models

data class User(
    var id: String? = null,
    var username: String? = null,
    val friends: List<String>? = listOf(),
    val assigments: List<Assigment>? = listOf()
){
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "id" to this.id,
            "username" to this.username,
            "friends" to this.friends,
            "assigments" to this.assigments
        )
    }
}
