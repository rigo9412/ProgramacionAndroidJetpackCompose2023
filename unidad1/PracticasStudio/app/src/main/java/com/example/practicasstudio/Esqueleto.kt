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
import com.example.practicasstudio.ui.theme.PracticasStudioTheme
import com.example.practicasstudio.ui.theme.eyesGray
import com.example.practicasstudio.ui.theme.noseGray
import com.example.practicasstudio.ui.theme.baseGray


class Esqueleto : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticasStudioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SkeletonDraw()
                }
            }
        }
    }


    fun siguiente(){
        val sig = Intent(this, MainActivity::class.java)
        startActivity(sig)
    }



    @Composable
    fun SkeletonDraw() {
        val context = LocalContext.current
        SkeletonBackground {
            ConstraintLayout(modifier = Modifier
                .clickable(onClick = {
                    Toast
                        .makeText(context, "clap clap", Toast.LENGTH_LONG)
                        .show()
                    siguiente()
                }
                )
                .fillMaxSize()) {
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
                .background(eyesGray)
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
                .background(noseGray)
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
        PracticasStudioTheme() {
            SkeletonDraw()
        }
    }


}
