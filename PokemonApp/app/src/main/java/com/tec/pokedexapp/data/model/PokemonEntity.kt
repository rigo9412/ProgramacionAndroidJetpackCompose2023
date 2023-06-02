package com.tec.pokedexapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon")
data class PokemonEntity (
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    val name: String,
    val type1: String,
    val type2: String,
    val total: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spAtk: Int,
    val spDef: Int,
    val speed: Int,
    val generation: Int,
    val legendary: Boolean,
    val discovered: Boolean = false
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
    legendary
)

fun Pokemon.toEntity(): PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        type1 = type1,
        type2 = type2,
        total = total,
        hp = hp,
        attack = attack,
        defense = defense,
        spAtk = spAtk,
        spDef = spDef,
        speed = speed,
        generation = generation,
        legendary = legendary,
        discovered = discovered
    )
}

fun PokemonEntity.toModel(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        type1 = type1,
        type2 = type2,
        total = total,
        hp = hp,
        attack = attack,
        defense = defense,
        spAtk = spAtk,
        spDef = spDef,
        speed = speed,
        generation = generation,
        legendary = legendary,
        discovered = discovered
    )
}