package com.rigo9412.poketest.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ViewEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @NonNull
    @ColumnInfo(name = "id_pokemon")
    val idPokemon: Int,
    @NonNull
    @ColumnInfo(name = "id_user")
    val idUser: Int
)