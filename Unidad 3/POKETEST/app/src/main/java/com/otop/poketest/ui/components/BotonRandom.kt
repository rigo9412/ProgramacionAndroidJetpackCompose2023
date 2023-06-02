package com.otop.poketest.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.otop.poketest.MainViewModel
import com.otop.poketest.R
import com.otop.poketest.data.PokemonEntity
import kotlin.random.Random
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.delay

@SuppressLint("UnrememberedMutableState")
@Composable
fun ButtonsWithRandom(viewModel: MainViewModel, pokemon: PokemonEntity) {

    var differentButton by remember { mutableStateOf(Random.nextInt(0, 4)) }

    val buttons = (0..3).map { i ->
        if (i == differentButton) {
            Row(modifier = Modifier.offset(0.dp,40.dp)) {
                Button(
                    onClick = { viewModel.Correct() }, modifier = Modifier
                        .weight(1f)
                        .border(5.dp, color = Color.Blue)
                        ,colors = ButtonDefaults.buttonColors(backgroundColor = Color(255, 215, 0))

                ) {
                    Text(text = pokemon.name, color = Color.Black)
                }
            }

        } else {
            Row(modifier = Modifier.offset(0.dp,40.dp)) {
                Button(onClick = { viewModel.vidaMenos() }, modifier = Modifier
                    .weight(1f)
                    .border(5.dp, color = Color.Blue),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(255, 215, 0))) {
                    Text(text = viewModel.getRandomPokemon(true).name
                        ,color = Color.Black)
                }
            }

        }
    }

    buttons.forEach { button ->
        button
    }

    differentButton = Random.nextInt(0, 4)
}
