package com.example.maincraface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


@Preview(showBackground = true)

@Composable
fun DefaultPreviewSkull() {
       HeadSkull()

}
@Composable
fun HeadSkull(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background((Color.LightGray))
                .size(400.dp, 350.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp)

            ) {
                val face = createRef()
                FaceSkull(modifier = Modifier.constrainAs(face){
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
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        EyesSkull()
        NoseSkull()
        MouthSkull()
    }
}
@Composable
fun EyeSkull(){
    Box(modifier = Modifier
        .background(Color.Black)
        .size(100.dp, 50.dp))
}
@Composable
fun EyesSkull(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        EyeSkull()
        EyeSkull()
    }
}
@Composable
fun NoseSkull(){
    Box(
        modifier = Modifier
            .background(Color.Gray)
            .size(100.dp, 50.dp)
    )
}
@Composable
fun MouthSkull(){
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .height(50.dp)
    )
}