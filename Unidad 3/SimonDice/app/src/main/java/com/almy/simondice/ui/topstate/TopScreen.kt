package com.almy.simondice.ui.topstate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.almy.simondice.domain.models.Player
import com.almy.simondice.ui.theme.*


@Composable
fun TopScreen(viewModel: TopViewModel/*, simonViewModel: SimonViewModel*/)
{
    val state = viewModel.uiState.collectAsState().value
    when (state) {
        is TopScreenState.Ready -> ScreenReady(top = state.top, viewModel = viewModel)
        is TopScreenState.Error -> ScreenError(msg = state.message)
        is TopScreenState.Loading -> ScreenLoading(viewModel = viewModel)
    }
}

@Composable
fun ScreenReady(
    top: List<Player>,
    viewModel: TopViewModel,
    //simonViewModel: SimonViewModel
){
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(20.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Leaderboard",
                fontSize = 30.sp
            )

        }
        var n = 0
        top.sortedByDescending { it.score }.forEach {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(it.name, fontSize = 20.sp)
                Text(it.level.toString(), fontSize = 20.sp)
                Text(it.score.toString(), fontSize = 20.sp)
            }
            n++
        }
    }
}


@Composable
fun ScreenError(
    msg: String
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = msg)
    }
}

@Composable
fun ScreenLoading(
    viewModel: TopViewModel
){
    //viewModel.postTopFake(Player(null,"test4",5,1))
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Cargando...", fontSize = 20.sp)
    }
}

