package com.rigo9412.poketest.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    @NonNull
    val name: String,
    @NonNull
    @ColumnInfo(name = "type_1")
    val type1: String,
    @ColumnInfo(name = "type_2")
    val type2: String,
    @NonNull
    val total: Int,
    @NonNull
    val hp: Int,
    @NonNull
    val attack: Int,
    @NonNull
    val defense: Int,
    @NonNull
    @ColumnInfo(name = "sp_atk")
    val spAtk: Int,
    @NonNull
    @ColumnInfo(name = "sp_def")
    val spDef: Int,
    @NonNull
    val speed: Int,
    @NonNull
    val generation: Int,
    @NonNull
    val legenday: String,
)

fun PokemonEntity.asExternalModel() = Pokemon(
    id,
    name,
    type1,
    type2,
    total,
    hp,
    attack,
    defense,
    spAtk,
    spDef,
    speed,
    generation,
)

