package com.example.caramaincra


import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun DefaultPreviewSkull(){
    HeadSkull()
}

@Composable
fun HeadSkull(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(modifier = Modifier
            .background(Color.LightGray)
            .width(400.dp)
            .height(300.dp)

        ){
            ConstraintLayout (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(60.dp))
            {
                val face = createRef()
                FaceSkull(modifier = Modifier.constrainAs(face) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            }
        }

    }
}
@Composable
fun FaceSkull(modifier: Modifier){
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EyesSkull()
        NoseSkull()
        Mouth()

    }
}

@Composable
fun EyesSkull(){
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        EyeSkull()
        EyeSkull()
    }
}

@Composable
fun Mouth(){
    Box(modifier = Modifier
        .background(Color.Black)
        .fillMaxWidth()
        .height(50.dp)
    )
}

@Composable
fun NoseSkull(){
    Box(modifier = Modifier
        .background(Color.Gray)
        .width(100.dp)
        .height(50.dp))
}

@Composable
fun EyeSkull(){
    Box(modifier = Modifier
        .background(Color.Black)
        .width(100.dp)
        .height(50.dp))
}