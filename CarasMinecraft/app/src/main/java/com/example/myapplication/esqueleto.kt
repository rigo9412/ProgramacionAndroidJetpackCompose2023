package com.example.myapplication

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.ui.theme.MyApplicationTheme

class esqueleto : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    HeadSkeleton(null)
                }
            }
        }
    }
}

val baseOffWhite = Color(0xFFC4C3C3)
val mouthBlack= Color(0xFF303030)
val noseGrey = Color(0xFF818181)

@Composable
fun HeadSkeleton(context: Context?){
    Box(modifier = Modifier
        .background(baseOffWhite)
        .width(600.dp)
        .height(400.dp)
        .clickable {
            MediaPlayer
                .create(context, R.raw.skeleton)
                .start()
        }){
        ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(47.dp)
            ) {
                FaceSk()
            }
    }
//    Column(
//        modifier = Modifier.fillMaxSize().clickable { onClick() },
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//
//        ) {
//        Box(
//            modifier = Modifier
//                .background(baseOffWhite)
//                .width(600.dp)
//                .height(400.dp)
//        ) {
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(47.dp)
//            ) {
//                FaceSk()
//            }
//        }
//    }
}


@Composable
fun FaceSk(){
    ConstraintLayout(modifier = Modifier.fillMaxSize(),){
        val (eyes,nose,mouth) = createRefs()
        EyesSk(modifier = Modifier.constrainAs(eyes){
            bottom.linkTo(nose.top)
        })
        NoseSk(modifier = Modifier.constrainAs(nose){
            top.linkTo(parent.top,80.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        MouthSk(modifier = Modifier.constrainAs(mouth){
            top.linkTo(parent.top,180.dp)
            bottom.linkTo((parent.bottom))
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun EyesSk(modifier: Modifier){
    Row(
        modifier = modifier
            .width(300.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        EyeSk(left = true)
        EyeSk(left = false)
    }
}

@Composable
fun NoseSk(modifier: Modifier){
    Row(
        modifier = modifier
            .width(100.dp)
            .height(50.dp)
            .background(noseGrey),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
    }
}

@Composable
fun MouthSk(modifier: Modifier){
    Row(
        modifier = modifier
            .width(300.dp)
            .height(50.dp)
            .background(mouthBlack),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
    }
}

@Composable
fun EyeSk(left: Boolean){
    Row{
        Box(modifier = Modifier
            .background(mouthBlack)
            .width(50.dp)
            .height(50.dp)
        )
        Box(modifier = Modifier
            .background(mouthBlack)
            .width(50.dp)
            .height(50.dp)
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyApplicationTheme {
        HeadSkeleton(null)
    }
}