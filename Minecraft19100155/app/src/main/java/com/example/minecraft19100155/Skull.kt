package com.example.minecraft19100155

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Preview(showBackground = true)
@Composable
fun DefaultPreviewSkull() {
    //HeadSkull({})
}

@Composable
fun EyeSkeleton() {
    Row() {
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(100.dp)
                .background(Color.Black)
        )

    }
}

@Composable
fun EyesSkeleton() {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        EyeSkeleton()
        EyeSkeleton()

    }
}

@Composable
fun HeadSkull(context: Context?) {
    Column(
        modifier = Modifier.fillMaxSize()
            .clickable {
                MediaPlayer
                    .create(context, R.raw.creeper)
                    .start()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.background(Color.LightGray).width(600.dp).height(400.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize().padding(45.dp),
            ) {
                val face = createRef()
                FaceSkeleton(modifier = Modifier.constrainAs(face){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            }
        }
    }
}

@Composable
fun FaceSkeleton(modifier: Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        EyesSkeleton()
        NoseSkeleton()
        MouthSkeleton()
    }
}

@Composable
fun NoseSkeleton() {
    Row() {
        Box(
            modifier = Modifier.background(Color.Gray).height(50.dp).width(100.dp)
        )
    }
}

@Composable
fun MouthSkeleton() {
    Row() {
        Box(
            modifier = Modifier.background(Color.Black).height(50.dp).fillMaxWidth()
        )
    }
}
