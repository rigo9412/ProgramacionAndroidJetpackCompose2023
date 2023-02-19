package com.example.practicasstudio

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
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
import com.example.practicasstudio.ui.theme.PracticasStudioTheme
import com.example.practicasstudio.ui.theme.basePink
import com.example.practicasstudio.ui.theme.nosePink
import com.example.practicasstudio.ui.theme.nosePinkDark

import com.example.practicasstudio.Creeper





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticasStudioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    Pig()
                }
                }
            }
        }
    fun siguiente(){
        val sig = Intent(this, Creeper::class.java)
        startActivity(sig)}



    @Composable
    fun Pig() {

        val context = LocalContext.current
        PigBackground {
            ConstraintLayout(modifier = Modifier
                .clickable(onClick = {
                    Toast
                        .makeText(context, "Oink oink", Toast.LENGTH_LONG)
                        .show()
                    siguiente()
                }
                )
                .fillMaxSize()) {
                val (eyes, nose) = createRefs()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .constrainAs(eyes) {
                            bottom.linkTo(nose.top)
                        }, horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PigEye(position = "left")
                    PigEye()
                }

                PigNose(modifier = Modifier.constrainAs(nose) {
                    top.linkTo(parent.top, 100.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            }
        }
    }


    @Composable
    fun PigBackground(function: @Composable () -> Unit) {
        Box(
            androidx.compose.ui.Modifier
                .background(basePink)
                .size(400.dp)
        ) { function() }
    }

    @Composable
    fun PigEye(position: String = "right") {
        Row(
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
                .rotate(if (position == "left") 180.0F else 0.0F)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .size(50.dp)
                )
            }
            Column {
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .size(50.dp)
                )
            }
        }
    }

    @Composable
    fun PigNose(modifier: Modifier) {
        Row(
            modifier = modifier
                .width(195.dp)
                .height(150.dp)
                .background(nosePink),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PigNostrils()
            PigNostrils()
        }
    }

    @Composable
    fun PigNostrils() {
        Box(
            modifier = androidx.compose.ui.Modifier
                .background(nosePinkDark)
                .width(50.dp)
                .height(70.dp)
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PracticasStudioTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                Pig()
            }
        }
    }

    }





