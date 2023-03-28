package com.rfcpractica.result

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rfcpractica.form.ui.FormViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResultScreen(
    curp: String,
    name: String,
    navigationController: NavHostController,
    viewModel: FormViewModel
): FormViewModel {
    var fvm = FormViewModel()
    fvm.initState()
    ResultView(curp) {
        navigationController.navigate("form")
    }
    return fvm
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
            fontSize = 50.sp
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