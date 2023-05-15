package com.tec.pokedexapp.ui.navigator.main.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun LeaderboardScreen(navController: NavHostController, globalProvider: GlobalProvider) {
    val tops = listOf(
        User(
            id = 1,
            name = "Ana",
            country = "México",
            startDate = "2022-01-01",
            finishDate = "2022-01-10",
            topScore1 = 50,
            topScore2 = 75,
            topScore3 = 90,
            triesToFinish = 3,
            minutesToFinish = 120
        ),
        User(
            id = 2,
            name = "Juan",
            country = "España",
            startDate = "2022-01-01",
            finishDate = "2022-01-10",
            topScore1 = 50,
            topScore2 = 75,
            topScore3 = 90,
            triesToFinish = 2,
            minutesToFinish = 122
        ),
        User(id = 3,
            name = "Maria",
            country = "Estados Unidos",
            startDate = "2022-01-01",
            finishDate = "2022-01-10",
            topScore1 = 50,
            topScore2 = 75,
            topScore3 = 90,
            triesToFinish = 4,
            minutesToFinish = 140
        ),
        User(
            id = 4,
            name = "Carlos",
            country = "México",
            startDate = "2022-01-01",
            finishDate = "2022-01-10",
            topScore1 = 50,
            topScore2 = 75,
            topScore3 = 90,
            triesToFinish = 7,
            minutesToFinish = 180
        ),
        User(
            id = 5,
            name = "Andrea",
            country = "México",
            startDate = "2022-01-01",
            finishDate = "2022-01-10",
            topScore1 = 50,
            topScore2 = 75,
            topScore3 = 90,
            triesToFinish = 7,
            minutesToFinish = 180
        ),
        User(
            id = 6,
            name = "Daniel",
            country = "México",
            startDate = "2022-01-01",
            finishDate = "2022-01-10",
            topScore1 = 50,
            topScore2 = 75,
            topScore3 = 90,
            triesToFinish = 7,
            minutesToFinish = 180
        ),
        User(
            id = 4,
            name = "Jaime",
            country = "México",
            startDate = "2022-01-01",
            finishDate = "2022-01-10",
            topScore1 = 50,
            topScore2 = 75,
            topScore3 = 90,
            triesToFinish = 7,
            minutesToFinish = 180
        ),
        User(
            id = 7,
            name = "Gael",
            country = "México",
            startDate = "2022-01-01",
            finishDate = "2022-01-10",
            topScore1 = 50,
            topScore2 = 75,
            topScore3 = 90,
            triesToFinish = 7,
            minutesToFinish = 180
        )
    )

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

fun getFlagEmoji(country: String): String { //Nota: el pais tiene que esta en español
    val countryCode = Locale.getISOCountries()
        .firstOrNull { Locale("", it).displayCountry.equals(country, ignoreCase = true) }
    return countryCode?.let {
        val firstLetterCodePoint = Character.codePointAt(it, 0) - Character.codePointAt("A", 0) + 0x1F1E6
        val secondLetterCodePoint = Character.codePointAt(it, 1) - Character.codePointAt("A", 0) + 0x1F1E6
        String(Character.toChars(firstLetterCodePoint)) + String(Character.toChars(secondLetterCodePoint))
    } ?: ""
}
