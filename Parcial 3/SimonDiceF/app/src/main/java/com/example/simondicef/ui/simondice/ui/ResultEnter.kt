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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.simondicef.domain.models.User
import com.example.simondicef.leaderboard.ui.LeaderboardViewModel
import com.example.simondicef.leaderboard.ui.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun resultView(viewModel: LeaderboardViewModel, score: Int, userViewModel: UserViewModel){
    val leaderboard = viewModel.top10.collectAsState().value
    var nameEntry = viewModel.entry.collectAsState().value
    var isLeaderboardUpdated by remember { mutableStateOf(false)}
    val coroutineScope = rememberCoroutineScope()

    if(viewModel.checkIfTop(score) && nameEntry){
        nameEntry(viewModel = viewModel,score,userViewModel)
    }
    else{
        LaunchedEffect(Unit){
            val users = coroutineScope.async(Dispatchers.IO) {
                userViewModel.getUsers()
            }.await()
            viewModel.getLeaderBoard(users)
            isLeaderboardUpdated = true
        }
        if(isLeaderboardUpdated) {
            showLeaderboard(leaderboard = leaderboard)
            Button(onClick = {
                viewModel.changeJustShow(false)
                viewModel.goBack(true)
            }) {
                Text(text = "Regresar")
            }
        }
    }

}

@Composable
fun nameEntry(viewModel: LeaderboardViewModel, score: Int,userViewModel: UserViewModel){
    val focusRequester = remember { FocusRequester() }
    val inputState = remember { mutableStateOf("") }

    OutlinedTextField(
        value = inputState.value,
        onValueChange = { newValue ->
            inputState.value = newValue
        },modifier = Modifier
            .testTag("name")
            .focusRequester(focusRequester),
        maxLines = 1
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Button(modifier = Modifier.testTag("entry"),onClick = {
        if(inputState.value != "") {
            viewModel.addScore(inputState.value, score)
            viewModel.viewModelScope.launch {
                val user = userViewModel.postUser(User(inputState.value,score))
                Log.d("POST RESPONSE",user.name)
            }
        }
    }) {
        Text(text = "AGREGAR PUNTAJE")
    }
}

@Composable
fun showLeaderboard(leaderboard: List<User>){
    Column() {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.background)
                .testTag("leaderboard"),
            contentPadding = PaddingValues(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 60.dp)
        ) {
            items(leaderboard) {
                ListField(user = it)
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
fun ListField(user: User){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = MaterialTheme.colors.background)
            .testTag("field"),
    ){
        Text(text = user.name!!, modifier = Modifier.testTag("player_name"))
        Text(text = user.score.toString())
    }
}

