package com.game.simondicevm.ui.topstate

import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rigo.simondice.domain.models.Player
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.game.simondicevm.ui.SimonViewModel
import com.game.simondicevm.ui.theme.*


@Composable
fun TopScreen(viewModel: TopViewModel, simonViewModel: SimonViewModel)
{
    val state = viewModel.uiState.collectAsState().value
    when (state) {
        is TopScreenState.Ready -> ScreenReady(top = state.top, viewModel = viewModel, simonViewModel = simonViewModel)
        is TopScreenState.Error -> ScreenError(msg = state.message)
        is TopScreenState.Loading -> ScreenLoading(viewModel = viewModel)
    }
}

@Composable
fun ScreenReady(
    top: List<Player>,
    viewModel: TopViewModel,
    simonViewModel: SimonViewModel
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(onClick = { simonViewModel.Inicio() }) {
                Text(text = "Regresar")
            }
        }

        var n = 0
        top.sortedByDescending { it.score }.forEach {
            Row(modifier = Modifier.padding(bottom = 10.dp)) {
                Caja(it.name, it.level, it.score, n)
            }
            n++
        }
    }
}

//colores que uso para mis cajas :>
var colores: List<Color> = listOf(golden, silverDark, bronze)
var coloresIcono: List<Color> = listOf(goldenDark, silver, bronzeDark)

@Composable
fun Caja(
    name: String,
    level: Int,
    score: Int,
    n: Int
)
{
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(if (n < 3) colores[n] else gray)
            .padding(20.dp)
    ) {
        Column() {
            Row(verticalAlignment = Alignment.CenterVertically){
                if(n<3)
                {
                    Icon(
                        Icons.Default.WorkspacePremium,
                        modifier = Modifier
                            .padding(end = 10.dp, top = 0.dp)
                            .size(70.dp),
                        contentDescription = "",
                        tint = coloresIcono[n]
                    )
                }
                Text(
                    text = (n+1).toString(),
                    fontSize = if(n<3) 30.sp else 17.sp,
                    fontWeight = if(n<3) FontWeight.Bold else FontWeight.Normal,
                    color = if (n<3) coloresIcono[n] else Color.Gray
                )
                Column(modifier = Modifier.padding(start = 20.dp)) {
                    Row(modifier = Modifier.padding(bottom = 5.dp)) {
                        Text(name, color = if (n<3) coloresIcono[n] else Color.Gray, fontWeight = FontWeight.SemiBold)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text(text = "Level: $level", color = if (n<3) coloresIcono[n] else Color.Gray, fontWeight = FontWeight.Medium)
                        Text(text = "Score: $score", color = if (n<3) coloresIcono[n] else Color.Gray, fontWeight = FontWeight.Medium)
                    }
                }
            }
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

/*@Composable
fun CustomButton(
    btnColor: Color,
    lblTexto: String,
    onClick : () -> Unit
){
    Button(
        onClick = { onClick },
        modifier = Modifier
            .padding(20.dp)
            .width(160.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(btnColor)
    ) {
        Text(lblTexto, fontWeight = FontWeight.Light, color = Color.White)
    }
}*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SimonDiceVMTheme {

    }
}