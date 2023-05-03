package com.ezequiel.simondice

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ezequiel.simondice.domain.modelo.Player
import com.ezequiel.simondice.models.Top10
import com.ezequiel.simondice.ui.theme.black

@Composable
fun SimonFullDetailDialog(player: Player, onDismissRequest: () -> Unit = { }, isVisible: Boolean) {
    //var x = isVisible
    Dialog (
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(570.dp)
                .padding(4.dp),
            elevation = 4.dp,
            backgroundColor = black,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(10.dp),
                contentAlignment = Alignment.TopEnd
            ) {
               // ContentDialog(top10Pri, isVisible)
                CloseButton(onClick = {
                    onDismissRequest()

                })
            }
        }
    }
}

@Composable
fun HeaderLabelVertical(text:String){
    Box {
        Text(
            modifier = Modifier
                .vertical()
                .rotate(-90f)
                .padding(4.dp),
            text = text
        )
        Text(
            modifier = Modifier
                .vertical()
                .rotate(-90f)
                .padding(4.dp),
            text = text
        )
    }
}
fun Modifier.vertical() =
    layout {
            measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }

@Composable
fun ContentDialog(top10: Top10, isVisible: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(shape = RoundedCornerShape(5.dp))
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(5.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    //.background(Color(rgb(120, 98, 17)))
                    .background(Color.Black)
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(5.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    ),
                contentAlignment = Alignment.Center
            ){
 //               HeaderLabelVertical(text = if(isVisible) player.Nombre else "TOP 10")
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Spacer(modifier = Modifier.height(10.dp))

                top10.eliminarPara10()
                for (jug: Player in top10.list){

//                PropertyLabel(name = jug.Nombre, value = jug.Puntos.toString(), isVisible = isVisible)
                }

            }
        }
    }
}

@Composable
fun CloseButton(onClick : () -> Unit) {
    IconButton(
        modifier = Modifier
            .size(40.dp)
            .background(Color.Transparent),
        onClick = { onClick() }
    ) {
        Icon(Icons.Outlined.Close, "icon", tint = Color.Red)
    }
}

@Composable
fun PropertyLabel(name: String, value: String, isVisible: Boolean) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name + " ", modifier = Modifier.padding(4.dp).width(150.dp), fontWeight = FontWeight.Normal, fontSize = 13.sp, color = black)
        Text(text = if(isVisible) value else "???", modifier = Modifier.padding(4.dp),color = Color.White , fontWeight = FontWeight.Normal, fontSize = 13.sp)
    }
}

