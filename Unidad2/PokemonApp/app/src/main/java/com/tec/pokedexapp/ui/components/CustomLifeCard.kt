package com.tec.pokedexapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tec.pokedexapp.R

@Composable
fun CustomLifeCard(lives: Int, score: Int) {
    Card(
        elevation = 20.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Puntaje: $score", fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 20.dp))
            Text(text = "Vida:  ", fontWeight = FontWeight.Bold)

            repeat(lives){
                Image(
                    painter = painterResource(R.drawable.life),
                    contentDescription = "Vidas",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp)
                )
            }
        }
    }

}