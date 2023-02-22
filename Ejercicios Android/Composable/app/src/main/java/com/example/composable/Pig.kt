package com.example.composable

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composable.ui.theme.ComposableTheme
import com.example.composable.ui.theme.basePink
import com.example.composable.ui.theme.nosePink
import com.example.composable.ui.theme.nosePinkDark

class Pig : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Composable
fun Pig(context: Context) {
    PigBackground(onClickE = {
        MediaPlayer.create(context, R.raw.pig).start()
    }) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (eyes, nose) = createRefs()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .constrainAs(eyes) {
                        bottom.linkTo(nose.top)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
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
fun PigBackground(onClickE: () -> Unit, function: @Composable () -> Unit) {
    Box(
        Modifier
            .background(basePink)
            .size(400.dp)
            .clickable { onClickE() }) { function() }
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
        modifier = Modifier
            .background(nosePinkDark)
            .width(50.dp)
            .height(50.dp)
    )
}
