package com.example.generadorcurp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.generadorcurp.form.ui.components.btnEnter

@Composable
fun CurpView(action:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "CURP GENERADO:", fontWeight = FontWeight.Bold, fontSize = 25.sp, modifier = Modifier.padding(15.dp))
        btnEnter(enabled = true, generar = { action() }, content = "Ver CURP")
    }
}