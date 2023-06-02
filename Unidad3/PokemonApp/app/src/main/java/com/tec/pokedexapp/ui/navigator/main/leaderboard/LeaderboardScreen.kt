package com.tec.pokedexapp.ui.navigator.main.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tec.pokedexapp.data.constants.BackgroundRed
import com.tec.pokedexapp.data.model.User
import com.tec.pokedexapp.ui.game.Header
import com.tec.pokedexapp.ui.global.GlobalProvider
import java.util.Locale

@Composable
fun LeaderboardScreen(globalProvider: GlobalProvider, navController: NavHostController){
    val leaderboard = globalProvider.leaderboardVM.top10.collectAsState().value
    val loaded = globalProvider.leaderboardVM.loaded.collectAsState().value
    if(loaded) {
        Leaderboard(tops = leaderboard)
    }
    else{
        LoadingScreen()
    }
}

@Composable
fun Leaderboard(tops: List<User>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
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
            )
            {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Campeones ⭐",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(0.dp,0.dp,0.dp,10.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "No.",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 24.sp
                    )
                    Text(
                        text = "Nombre",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 22.sp
                    )
                    Text(
                        text = "País",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 22.sp,
                        textAlign = TextAlign.End,
                    )
                    Text(
                        text = "Min",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 22.sp,
                        textAlign = TextAlign.End,
                    )
                    Text(
                        text = "Ints",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 22.sp,
                        textAlign = TextAlign.End,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                tops.forEachIndexed { index, (id,name, country,startDate,finishDate, topScore1,topScore2,topScore3,minutesToFinish,triesToFinish) ->
                    val rowColor = when(index) {
                        0 -> Color(0xFFF0CA09)
                        1 -> Color(0xFF999E9C)
                        2 -> Color(0xFFC77024)
                        else -> Color.White
                    }
                    val fontSize = when(index) {
                        0, 1, 2 -> 10.sp
                        else -> 16.sp
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 5.dp,
                                        color = rowColor,
                                        shape = RoundedCornerShape(50.dp),
                                    )
                                    .padding(4.dp)
                                    .size(35.dp),
                                contentAlignment = Alignment.Center,
                            ){
                                Text(
                                    text = (index + 1).toString(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = if (index < 3) 20.sp else 24.sp,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                            Text(
                                text = name,
                                fontWeight = FontWeight.Bold,
                                color = if (index < 3) rowColor else Color.Black,
                                fontSize = if (index < 3) 20.sp else fontSize,
                                modifier = Modifier.width(100.dp)
                            )
                            Text(
                                text = getFlagEmoji(country),
                                fontWeight = FontWeight.Bold,
                                fontSize = if (index < 3) 20.sp else fontSize,
                                textAlign = TextAlign.End,
                                modifier = Modifier.width(40.dp)
                            )
                            Text(
                                text = triesToFinish.toString(),
                                fontWeight = FontWeight.Bold,
                                color = if (index < 3) rowColor else Color.Black,
                                fontSize = if (index < 3) 28.sp else fontSize,
                                textAlign = TextAlign.End,
                                modifier = Modifier.width(50.dp)
                            )
                            Text(
                                text = minutesToFinish.toString(),
                                fontWeight = FontWeight.Bold,
                                color = if (index < 3) rowColor else Color.Black,
                                fontSize = if (index < 3) 28.sp else fontSize,
                                textAlign = TextAlign.End,
                                modifier = Modifier.width(50.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Blue,
            strokeWidth = 5.dp,
            modifier = Modifier.size(50.dp)
        )
    }
}

fun getFlagEmoji(countryCode: String): String { //Nota: el pais tiene que esta en español
    val countryCodeUpperCase = countryCode.uppercase()
    val firstLetter = Character.codePointAt(countryCodeUpperCase, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(countryCodeUpperCase, 1) - 0x41 + 0x1F1E6
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}
