package com.example.registrocurp.curp.ui.result.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.registrocurp.GlobalProvider
import com.example.registrocurp.curp.ui.nav.Screens
import com.example.registrocurp.ui.theme.*

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
            .background(Color(46,139,87)),
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
            text = "$name tu CURP es: ",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Light,
            fontSize = 30.sp
        )
        Text(
            text = curp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp
        )
        OutlinedButton(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color(28, 87, 54)
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