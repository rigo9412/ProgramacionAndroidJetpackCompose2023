package com.almy.poketec.data

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun readJsonFile(context: Context, fileName: String): List<Pokemon> {
    val json: String?
    try {
        val inputStream: InputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charset.forName("UTF-8"))
    } catch (e: IOException) {
        e.printStackTrace()
        return emptyList()
    }
    val pokemonList: List<Pokemon> = Gson().fromJson(json, Array<Pokemon>::class.java).toList()
    return pokemonList
}


//este es namas una prueba
@Composable
fun MyScreen(
    context: Context,
    modifier: Modifier = Modifier
) {
    val pokemonList: List<Pokemon> = readJsonFile(context, "pokemon151.json")

    var poke: Pokemon = pokemonList[0]

    //textos namas pa testear
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),

        ) {
        Text(
            text = poke.name,
            fontSize = 45.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = poke.type1 + "/" + poke.type2,
            fontSize = 45.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = poke.total.toString(),
            fontSize = 45.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
    }
}