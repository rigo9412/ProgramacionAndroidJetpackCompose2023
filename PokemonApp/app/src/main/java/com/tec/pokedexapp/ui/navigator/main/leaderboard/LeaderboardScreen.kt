package com.tec.pokedexapp.ui.navigator.main.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tec.pokedexapp.data.constants.BackgroundRed
import com.tec.pokedexapp.ui.components.CustomButton
import com.tec.pokedexapp.ui.game.Header
import com.tec.pokedexapp.ui.global.GlobalProvider

@Composable
fun LeaderboardScreen(navController: NavHostController, globalProvider: GlobalProvider){
    Text("LEADERBOARD")
}

@Preview
@Composable
fun TopScreenPreview() {

    val tops = listOf(
        "John Doe" to 123,
        "Jane Doe" to 456,
        "Joe Bloggs" to 789,
        "John Doe" to 2223,
        "Jane Doe" to 4356,
        "Joe Bloggs" to 7789
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundRed)
    ) {
        Header(title = "Tops Mundiales 🏆")
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Campeón",
                        fontWeight = FontWeight.Bold,
                        color = Color.Yellow,
                        fontSize = 24.sp
                    )
                    Text(
                        text = "Puntaje",
                        fontWeight = FontWeight.Bold,
                        color = Color.Yellow,
                        fontSize = 24.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(100.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                tops.forEachIndexed { index, (name, score) ->
                    val rowColor = when(index) {
                        0 -> Color(0xFF3F51B5)
                        1 -> Color(0xFF4CAF50)
                        2 -> Color(0xFFFF9800)
                        else -> Color.White
                    }
                    val nameColor = when(index) {
                        0, 1, 2 -> Color.White
                        else -> Color.Black
                    }
                    val fontSize = when(index) {
                        0 , 1, 2 -> 10.sp
                        else -> 16.sp
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            Box(
                                modifier = Modifier
                                    .background(rowColor)
                                    .padding(4.dp)
                                    .size(35.dp)
                                    .clip(CircleShape),
                                contentAlignment = Alignment.Center,
                                content = {
                                    Text(
                                        text = (index + 1).toString(),
                                        fontWeight = FontWeight.Bold,
                                        color = nameColor,
                                        fontSize = if (index < 3) 28.sp else 24.sp
                                    )
                                }
                            )
                            Text(
                                text = name,
                                fontWeight = FontWeight.Bold,
                                color = if (index < 3) Color.Blue else nameColor,
                                fontSize = if (index < 3) 28.sp else fontSize
                            )
                            Text(
                                text = score.toString(),
                                fontWeight = FontWeight.Bold,
                                color = if (index < 3) Color.Blue else nameColor,
                                fontSize = if (index < 3) 28.sp else fontSize,
                                textAlign = TextAlign.End,
                                modifier = Modifier.width(100.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = "Regresar",
            enabled = true,
            onClick = {

            })
    }
}