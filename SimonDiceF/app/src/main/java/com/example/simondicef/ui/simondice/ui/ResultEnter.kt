package com.example.simondicef.ui.simondice.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondicef.leaderboard.ui.LeaderboardViewModel

@Composable
fun resultView(viewModel: LeaderboardViewModel, score: Int){
    val leaderboard = viewModel.top10.collectAsState().value
    var nameEntry = viewModel.entry.collectAsState().value

    Log.d("STATE",nameEntry.toString())
    if(viewModel.checkIfTop(score) && nameEntry){
        nameEntry(viewModel = viewModel,score)
    }
    else{
        showLeaderboard(leaderboard = leaderboard)
        Button(onClick = {
            viewModel.goBack(true)
        }) {
            Text(text = "Regresar")
        }
    }

}

@Composable
fun nameEntry(viewModel: LeaderboardViewModel, score: Int){
    val inputState = remember { mutableStateOf("") }
    OutlinedTextField(
        value = inputState.value,
        onValueChange = { newValue ->
            inputState.value = newValue
        },
        maxLines = 1
    )
    Button(onClick = {
        viewModel.addScore(inputState.value,score)
    }) {
        Text(text = "AGREGAR PUNTAJE")
    }
}

@Composable
fun showLeaderboard(leaderboard: List<Pair<String,Int>>){
    Column() {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.background),
            contentPadding = PaddingValues(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 60.dp)
        ) {
            items(leaderboard) {
                ListField(pair = it)
            }
            if (leaderboard.isEmpty()) {
                item() {
                    Text(
                        text = "No hay ningun valor", fontSize = 20.sp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ListField(pair : Pair<String,Int>){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = MaterialTheme.colors.background),
    ){
        Text(text = pair.first)
        Text(text = pair.second.toString())
    }
}

