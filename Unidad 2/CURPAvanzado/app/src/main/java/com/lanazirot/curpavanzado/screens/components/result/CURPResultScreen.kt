package com.lanazirot.curpavanzado.screens.components.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.provider.LocalGlobalProvider

@Composable
fun CURPResultScreen(
    curp: String,
    name: String,
    lastname: String,
) {

    val gp = LocalGlobalProvider.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "CURP: $curp",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Nombre: $name $lastname",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            imageVector = androidx.compose.material.icons.Icons.Default.Check,
            contentDescription = "Check",
            tint = Color.Green,
            modifier = Modifier.size(64.dp)
        )
        Button(onClick = {
            gp.nav.navigate(Routes.Welcome.route)
        }) {
            Text(text = "Regresar")
        }

    }
}
