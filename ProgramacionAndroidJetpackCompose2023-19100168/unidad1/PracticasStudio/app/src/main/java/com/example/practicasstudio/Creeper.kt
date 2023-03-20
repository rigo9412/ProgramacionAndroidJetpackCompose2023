package com.example.practicasstudio

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.practicasstudio.ui.theme.GreenPlus
import com.example.practicasstudio.ui.theme.PracticasStudioTheme
import com.example.practicasstudio.ui.theme.baseGreen
import com.example.practicasstudio.ui.theme.creeperOtro


class Creeper : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticasStudioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreeperDraw()

                }
            }
        }
    }


    fun siguiente(){
        val sig = Intent(this, Esqueleto::class.java)
        startActivity(sig)
    }



    @Composable
    fun CreeperDraw() {
        val context = LocalContext.current
        CreeperBackground {
            ConstraintLayout(modifier = Modifier.clickable(onClick = {
                Toast
                    .makeText(context, "TsssssTsssss", Toast.LENGTH_LONG)
                    .show()
                siguiente()
            }
            ).fillMaxSize()) {
                val (eyes, mouth, mouthFront) = createRefs()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(eyes) {
                            bottom.linkTo(mouth.top)
                        }
                ) {
                    CreeperEyes()
                }

                CreeperMouth(modifier = Modifier.constrainAs(mouth){
                    top.linkTo(parent.top, 200.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })


                CreeperMouthFront(modifier = Modifier.constrainAs(mouthFront){
                    top.linkTo(parent.top, 200.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            }
        }
    }

    @Composable
    fun CreeperBackground(function: @Composable () -> Unit) {
        Box(
            androidx.compose.ui.Modifier
                .background(baseGreen)
                .size(400.dp)
        ) { function()

        }
    }

    @Composable
    fun CreeperEye(position: String = "left") {
        Box(
            androidx.compose.ui.Modifier
                .background(creeperOtro)
                .size(100.dp)
        ){
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .size(50.dp)
                    .align(if (position == "left") Alignment.BottomEnd else Alignment.BottomStart)
            )
        }
    }

    @Composable
    fun CreeperEyes() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CreeperEye()
            CreeperEye("right")
        }
    }

    @Composable
    fun CreeperMouth(modifier: Modifier) {
        Row(
            modifier = modifier
                .height(200.dp)
                .width(200.dp)
                .background(Color.Transparent)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 50.dp)
                    .background(creeperOtro)
                    .size(50.dp)
            ) {

            }
            Column(
                modifier = Modifier
                    .height(150.dp)
                    .background(creeperOtro)
                    .size(50.dp)
            ) {

            }
            Column(
                modifier = Modifier
                    .height(150.dp)
                    .background(creeperOtro)
                    .size(50.dp)
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 50.dp)
                    .background(creeperOtro)
                    .size(50.dp)
            ) {

            }
        }
    }

    @Composable
    fun CreeperMouthFront(modifier: Modifier) {
        Row(
            modifier = modifier
                .height(120.dp)
                .width(200.dp)
                .background(Color.Transparent)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 50.dp)
                    .background(Color.Black)
                    .size(50.dp)
            ) {

            }
            Column(
                modifier = Modifier
                    .height(150.dp)
                    .background(Color.Black)
                    .size(50.dp)
            ) {

            }
            Column(
                modifier = Modifier
                    .height(150.dp)
                    .background(Color.Black)
                    .size(50.dp)
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 50.dp)
                    .background(Color.Black)
                    .size(50.dp)
            ) {

            }
        }
    }





    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview3() {
        PracticasStudioTheme {
            CreeperDraw()
        }
    }


}
