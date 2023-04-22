package com.curp.result

import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.curp.ui.result.ui.FormViewModel

@Composable
fun ResultScreen(
    curp: String,
    name: String,
    navigationController: NavHostController
) {
    ResultView(curp) {
        navigationController.popBackStack()
    }
}
@Composable
fun ResultView(curp: String, onClick: () -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            Icons.Default.CheckCircle,
            modifier = Modifier.size(100.dp),
            contentDescription = "DONE CURP",
            tint = Color.White
        )
        Text(
            text = curp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 52.sp
        )
        OutlinedButton(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow,
                contentColor = Color.Red
            )

        ) {
            Text(text = "Regresar")

        }

    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun PreviewResultView() {
    ResultView("CURP", {})
}