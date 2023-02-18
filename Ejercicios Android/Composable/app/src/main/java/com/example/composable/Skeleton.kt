package com.example.composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composable.ui.theme.*

class Skeleton : ComponentActivity() {
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
fun SkeletonDraw() {
    SkeletonBackground {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (eyes, nose, mouth) = createRefs()
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(eyes) {
                        bottom.linkTo(nose.top)
                    }, verticalAlignment = Alignment.Bottom
            ) {
                SkeletonEyes()
            }

            SkeltonNose(modifier = Modifier.constrainAs(nose) {
                top.linkTo(parent.top, 150.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

            SkeltonMouth(modifier = Modifier.constrainAs(mouth) {
                top.linkTo(nose.bottom)
            })

        }
    }
}

@Composable
fun SkeltonEye() {
    Box(
        modifier = Modifier
            .background(eyesGrayDark)
            .height(50.dp)
            .width(100.dp)
    )
}

@Composable
fun SkeletonEyes() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        SkeltonEye()
        SkeltonEye()
    }
}

@Composable
fun SkeltonNose(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(noseGrayDark)
            .height(50.dp)
            .width(97.dp)
    )
}

@Composable
fun SkeltonMouth(modifier: Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        SkeltonEye()
        SkeltonEye()
        SkeltonEye()
    }
}


@Composable
fun SkeletonBackground(function: @Composable () -> Unit) {
    Box(
        Modifier
            .background(baseGray)
            .size(400.dp)
    ) { function() }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposableTheme {
        SkeletonDraw()
    }
}