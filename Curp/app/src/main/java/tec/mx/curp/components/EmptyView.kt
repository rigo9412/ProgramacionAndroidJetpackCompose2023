package tec.mx.curp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun EmptyView(
    action: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Text(
            text = "CURP LISTO",
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp
        )
        OutlinedButton(
            onClick = { action() }) {
            Text(text = "VER CURP",color = Color.Blue)

        }

    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun PreviewEmptyView() {
    EmptyView( {})
}