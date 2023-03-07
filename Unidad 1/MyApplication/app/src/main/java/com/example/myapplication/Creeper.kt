package com.example.myapplication


import android.content.Context
import android.graphics.Paint.Align
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
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
import com.example.myapplication.ui.theme.CREEPER
//import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.ui.theme.DarkGreen
import com.example.myapplication.ui.theme.basePink


@Preview(showBackground = true)
@Composable
fun CreperPreview() {

    //HeadCreeper()
    //MouthCreeper()

}

//@Composable
//fun HeadCreeper(onClick: () -> Unit){
//    Column(
//        modifier = Modifier.fillMaxSize().clickable { onClick() },
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally){
//        Box(modifier = Modifier
//            .background(CREEPER)
//            .width(400.dp)
//            .height(400.dp)
//
//        ){
//            ConstraintLayout (
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(50.dp))
//            {
//                val face = createRef()
//                FaceCreeper(modifier = Modifier.constrainAs(face) {
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                })
//
//            }
//        }
//
//    }
//}

//@Composable
//fun HeadCreeper(context: Context) {
//    Box(
//        modifier = Modifier
//            .background(CREEPER)
//            .width(400.dp)
//            .height(400.dp)
//            .clickable {
//                MediaPlayer
//                    .create(context, R.raw.creeper)
//                    .start()
//            }
//    )
//
//
//}

@Composable
fun HeadCreeper(context: Context){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Box(
            modifier = Modifier
                .background(CREEPER)
                .width(400.dp)
                .height(400.dp)
                .clickable {
                    MediaPlayer
                        .create(context, R.raw.skeleton)
                        .start()
                }
        ){
            ConstraintLayout (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp))
            {
                val face = createRef()
                FaceCreeper(modifier = Modifier.constrainAs(face) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            }
        }


    }
}





@Composable
fun FaceCreeper(modifier: Modifier){
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally){
        EyesCreeper()
        //NoseCreeper()
        //MouthCreeper()
        //MouthDownCreeper()
        MouthUpCreeper()

    }
}

@Composable
fun EyesCreeper(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        EyeCreeper()
        EyeCreeper2()
    }
}

@Composable
fun MouthDownCreeper(){
    Box(modifier = Modifier
        .background(DarkGreen)
        .width(50.dp)
        .height(50.dp))
}

@Composable
fun MouthCreeper(){
    Box(modifier = Modifier
        .background(Color.Black)
        .width(200.dp)
        .height(50.dp))
}

@Composable
fun MouthUpCreeper(){
    Row() {
        Box(modifier = Modifier
            .background(DarkGreen)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(DarkGreen)
            .width(50.dp)
            .height(50.dp))
    }
    Row() {
        Box(modifier = Modifier
            .background(DarkGreen)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(DarkGreen)
            .width(50.dp)
            .height(50.dp))
    }
    Row() {
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
    }
    Row() {
        Box(modifier = Modifier
            .background(DarkGreen)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(CREEPER)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(CREEPER)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(DarkGreen)
            .width(50.dp)
            .height(50.dp))
    }
}


@Composable
fun EyeCreeper(){
    Box(modifier = Modifier
        .background(Color.Black)
        .width(100.dp)
        .height(80.dp),
        contentAlignment = Alignment.BottomEnd){
        Box(
            modifier = Modifier
                .background(DarkGreen)
                .width(50.dp)
                .height(50.dp)
        )
    }
}
@Composable
fun EyeCreeper2(){
    Box(modifier = Modifier
        .background(Color.Black)
        .width(100.dp)
        .height(80.dp),
        contentAlignment = Alignment.BottomStart){
        Box(
            modifier = Modifier
                .background(DarkGreen)
                .width(50.dp)
                .height(50.dp)
        )
    }
}
@Composable
fun NoseCreeper(){
    Box(modifier = Modifier
        .background(DarkGreen)
        .width(100.dp)
        .height(50.dp))
}