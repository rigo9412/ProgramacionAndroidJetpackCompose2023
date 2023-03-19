package com.example.generadorcurp.result

import android.content.ClipData
import android.content.ClipboardManager
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.generadorcurp.form.ui.components.FormViewModel
import com.example.generadorcurp.form.ui.components.btnEnter

@Composable
fun ResultadoScreen(curp: String, navigationController: NavHostController, viewModel: FormViewModel){

    Log.d("CURP",curp)

    ResultadoView(curp){
        viewModel.initState()
        navigationController.navigate("formulario")
    }
}

@Composable
fun ResultadoView(curp: String, function: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        //var clipdata = ClipData.newPlainText("CURP",curp)

        Icon(Icons.Default.Done, modifier = Modifier.size(100.dp), contentDescription = "DONE", tint = Color.White)
//        Button(onClick = {clipboardManager.setPrimaryClip(clipdata) }){
//            Text(text = "Copiar")
//        }
        Text(text = "CURP: ", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(15.dp))
        TextField(value = curp, onValueChange = {}, enabled = false,modifier = Modifier.padding(15.dp))

        btnEnter(enabled = true, generar = { function() }, content = "Regresar")

    }
}
