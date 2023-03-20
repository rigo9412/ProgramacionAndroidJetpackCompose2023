package tec.mx.curp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorView(
    error: String = "error",
    onInitAction: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF018786))
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            Icons.Default.Warning,
            modifier = Modifier.size(100.dp),
            contentDescription = "Error al crear CURP",
            tint = Color.Yellow
        )
        Text(
            text = "Ocurrio un error inesperado 💔",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp,
            modifier = Modifier.padding(20.dp)
        )
        OutlinedButton(
            onClick = { onInitAction() }) {
            Text(text = "Reiniciar",color = Color.Black)

        }

    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun PreviewErrorView() {
    ErrorView("error", {})
}