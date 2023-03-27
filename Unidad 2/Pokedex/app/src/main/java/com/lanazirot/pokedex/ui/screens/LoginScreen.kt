package com.lanazirot.pokedex.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.pokedex.R

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.app_background),
                contentScale = ContentScale.Crop,
                alpha = 0.5f
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokemon),
                contentDescription = "Pokeball"
            )
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(20),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = androidx.compose.ui.graphics.Color.Black
                )
            ) {
                Text(
                    text = "Jugar",
                    modifier = Modifier.padding(10.dp),
                    color = androidx.compose.ui.graphics.Color.White,
                    fontSize = 30.sp
                )
                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "Pokeball",
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}