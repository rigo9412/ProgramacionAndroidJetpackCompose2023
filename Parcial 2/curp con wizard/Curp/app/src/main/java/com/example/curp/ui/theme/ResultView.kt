package com.example.curp.ui.theme

import android.graphics.Paint.Align
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.curp.GlobalProvider
import com.example.curp.ui.theme.greenDark
import com.example.curp.ui.theme.green


@Composable
fun ResultScreen(
    curp: String,
    name: String,
) {
    val navigationController = GlobalProvider.current.nav
    ResultView(curp,name) {
        navigationController.navigate(route = Screens.HomeScreen.route){
            popUpTo(navigationController.graph.id){
                inclusive = true
            }
        }
    }
}
@Composable
fun ResultView(curp: String,name:String, onClick: () -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            Icons.Default.CheckCircle,
            modifier = Modifier.size(80.dp),
            contentDescription = "DONE CURP",
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "$name tu CURP es el siguiente: ",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Light,
            fontSize = 28.sp
        )
        Text(
            text = curp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        OutlinedButton(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = greenDark,
                contentColor = Color.White
            )

        ) {
            Text(text = "Volver al Inicio")

        }

    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun PreviewResultView() {
    ResultView("CURP","tu nombre",{});
}
