package com.otop.poketest.ui.components

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.otop.poketest.MainViewModel
import java.io.InputStream
import com.otop.poketest.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileCard(viewModel:MainViewModel) {
    val inputStream: InputStream = viewModel.assetMannager.open("other/PokeTrainer.png")
    val bitmap = BitmapFactory.decodeStream(inputStream)
    val painter = rememberAsyncImagePainter(bitmap)
    val darkModeBG = if (viewModel.darkMode.value) {
        Color.Black
    } else {
        Color.White
    }
    val darkModeTXT = if (!viewModel.darkMode.value) {
        Color.Black
    } else {
        Color.White
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(darkModeBG)
    ) {
        Image(
            painter = painter,
            contentDescription = "Trainer Profile Picture",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Entrenador: Yo",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = darkModeTXT
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Edad: ???",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = darkModeTXT
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = viewModel.viewList.value.count().toString(),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = darkModeTXT
                )
                Text(
                    text = "Pokémon",
                    style = MaterialTheme.typography.caption,
                    color = darkModeTXT
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "4",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = darkModeTXT
                )
                Text(
                    text = "Badges",
                    style = MaterialTheme.typography.caption,
                    color = darkModeTXT
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "0",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = darkModeTXT
                )
                Text(
                    text = "Wins",
                    style = MaterialTheme.typography.caption,
                    color = darkModeTXT
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "0",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = darkModeTXT
                )
                Text(
                    text = "Losses",
                    style = MaterialTheme.typography.caption,
                    color = darkModeTXT
                )
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = viewModel.biggestScore.value.toString(),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = darkModeTXT
                )
                Text(
                    text = "Biggest Score",
                    style = MaterialTheme.typography.caption,
                    color = darkModeTXT
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = viewModel.biggestSpree.value.toString(),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = darkModeTXT
                )
                Text(
                    text = "Biggest Spree",
                    style = MaterialTheme.typography.caption,
                    color = darkModeTXT
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = viewModel.intentos.value.toString(),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = darkModeTXT
                )
                Text(
                    text = "Attempts",
                    style = MaterialTheme.typography.caption,
                    color = darkModeTXT
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            ProgressBar(
                value = viewModel.viewList.value.size, max = viewModel.list.size,
                color = darkModeTXT
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        val uniqueTypes = viewModel.list.distinctBy { it.type1 }

        val typeCounts = viewModel.list.groupBy { it.type1 }
            .mapValues { it.value.size }

        val typeCountsview = viewModel.viewList.value.groupBy { it.type1 }
            .mapValues { it.value.size }

        Text(
            text = "Tipos Pokemon Descubiertos", fontWeight = FontWeight.ExtraBold,
            color = darkModeTXT
        )
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(uniqueTypes) { pokemon ->

                Row(verticalAlignment = Alignment.CenterVertically) {
                    var views = 0f
                    if (typeCountsview[pokemon.type1] != null) {
                        views = typeCountsview[pokemon.type1].toString().toFloat()
                    }

                    val inputStream: InputStream =
                        viewModel.assetMannager.open("insignias/${pokemon.type1}.png")
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    val painter = rememberAsyncImagePainter(bitmap)
                    Text(
                        text = "${pokemon.type1}     ${views.toInt()}/${typeCounts[pokemon.type1].toString()}",
                        fontWeight = FontWeight.Bold,
                        color = darkModeTXT
                    )

                    val percentage =
                        (views / typeCounts[pokemon.type1].toString().toFloat()).coerceIn(0f, 1f)

                    if (views == typeCounts[pokemon.type1].toString().toFloat()) {
                        ImageWithProgressBar(
                            painter = painter,
                            progress = percentage,
                            isComplete = true
                        )
                    } else {
                        ImageWithProgressBar(
                            painter = painter,
                            progress = percentage,
                            isComplete = false
                        )
                    }

                }
            }
        }
    }
}