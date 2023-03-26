package com.game.curp.ui.result

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.game.curp.GlobalProvider
import com.game.curp.data.forms.ui.FormViewModel
import com.game.curp.ui.global.GlobalProvider
import com.game.curp.ui.nav.Screens
import com.game.curp.ui.theme.green
import com.game.curp.ui.theme.greenDark


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
            Icons.Default.Done,
            modifier = Modifier.size(80.dp),
            contentDescription = "",
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "$name, este es tu CURP :)"/*.replace("}","")*/,
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