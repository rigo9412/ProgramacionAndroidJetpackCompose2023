package com.tec.pokedexapp.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.pokedexapp.data.constants.DarkRed

@Composable
fun Header(title : String){
    Row(modifier = Modifier
        .height(60.dp)
        .fillMaxWidth()
        .background(DarkRed),
        horizontalArrangement = Arrangement.Center
    ){
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 40.sp, color = Color.White)
    }
}