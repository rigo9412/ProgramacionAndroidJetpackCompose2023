package com.tec.pokedexapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.pokedexapp.data.constants.YellowAccent

@Composable
fun CustomButton(modifier: Modifier,text :String, enabled : Boolean, onClick : () -> Unit){
    OutlinedButton(onClick = { onClick() },
        modifier = modifier
            .height(60.dp),
        colors= ButtonDefaults.buttonColors(
            backgroundColor = YellowAccent,
            disabledBackgroundColor = Color(0x54F3F0D1),
            contentColor = Color.Black,
            disabledContentColor = Color.White
        ),
        enabled = enabled,
        shape = RoundedCornerShape(50)
    ) {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 35.sp)
    }
}