package com.tec.pokedexapp

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tec.pokedexapp.ui.theme.PokedexAppTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            PokedexAppTheme {

                Surface(
                    Modifier.fillMaxSize(),
                    // background = ImageBitmap.imageResource(id = R.drawable.log_lofin)
                ) {
                    cargarLoggin()

                }
            }
        }
    }
}


@Composable
fun cargarLoggin() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.log_lofin),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        );
        Box(
            modifier = Modifier
                .padding(top = 60.dp, end = 20.dp)
                .height(120.dp)
                .width(140.dp)
                //.background(Color(0xFF000000))
                .align(Alignment.TopEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cabeza_picka),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            );
        }

        Column() {
            Spacer(modifier = Modifier.height(30.dp))

            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(50.dp)
                    .width(120.dp)
                    .background(Color.Yellow, RoundedCornerShape(24.dp))
                //.background(color = Color.Yellow)
            )
            {
                Text(text = "Jugar")
            }


            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier
                .height(50.dp)
                .width(120.dp)
                .background(Color.Yellow, RoundedCornerShape(24.dp)), onClick = { /*TODO*/ }
            ) {
                Text(text = "Pokedex")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier
                .height(50.dp)
                .width(120.dp)
                .background(Color.Yellow, RoundedCornerShape(24.dp)), onClick = { /*TODO*/ }) {
                Text(text = "Entrenador")
            }

        }

    }
}

