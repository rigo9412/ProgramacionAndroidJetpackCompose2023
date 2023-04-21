package com.example.desarrolloandroid

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
import com.example.desarrolloandroid.ui.theme.DesarrolloAndroidTheme
import com.example.desarrolloandroid.ui.theme.basepink
import com.example.desarrolloandroid.ui.theme.nosePinkDark
import com.example.desarrolloandroid.ui.theme.nosepink

//class Pig : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            DesarrolloAndroidTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                   Headpig({"Pig"})
//                }
//            }
//        }
//    }
//}

@Composable
fun Greeting4(name: String) {
    Text(text = "Hello $name!")
}


@Composable
fun Eye(left: Boolean){
    Row(){
        Box(modifier = Modifier
            .background(if (left) Color.Black else Color.White)
            .width(50.dp)
            .height(50.dp))

        Box(modifier = Modifier
            .background(if (left) Color.White else Color.Black)
            .width(50.dp)
            .height(50.dp))
    }
}

@Composable
fun Eyes(modifier: Modifier){
    Row(modifier=modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Eye(left = true)
        Eye(left = false)
    }
}

@Composable
fun Nostrils(){
    Box(modifier = Modifier
        .background(nosePinkDark)
        .width(40.dp)
        .height(40.dp))
}

@Composable
fun Nose(modifier: Modifier){
    Row(modifier = modifier
        .width(180.dp)
        .height(120.dp)
        .background(nosepink),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically) {
        Nostrils()
        Nostrils()
    }
}

@Composable
fun Face(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (eyes,nose)=createRefs()
        Eyes(modifier = Modifier.constrainAs(eyes) {
        bottom.linkTo(nose.top)
        })
        Nose(modifier = Modifier
            .constrainAs(nose){
                top.linkTo(parent.top,70.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}

//@Composable
//fun Headpig(onClick:()->Unit){
//    Column(
//        modifier = Modifier.fillMaxSize().clickable { onClick() },
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    )
//   {
//        Box(modifier = Modifier
//            .background(basepink)
//            .width(600.dp)
//            .height(400.dp)){
//            Face()
//        }
//
//   }
//}



@Composable
fun Headpig(context: Context){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
   {
       Box(modifier = Modifier
        .background(basepink)
        .height(400.dp)
        .width(400.dp)
        .clickable {
            MediaPlayer
                .create(context, R.raw.pig)
                .start()
        }){
        Face()
    }

   }
}



//@Composable
//fun Headpig(context: Context?){
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    )
//
//
//    Box(modifier = Modifier
//        .background(basepink)
//        .height(400.dp)
//        .width(400.dp)
//        .clickable {
//            MediaPlayer
//                .create(context, R.raw.pig)
//                .start()
//        }){
//        Face()
//    }
//    }
//}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview4() {
//    DesarrolloAndroidTheme {
//      Headpig({"Pig"})
//    }
//}