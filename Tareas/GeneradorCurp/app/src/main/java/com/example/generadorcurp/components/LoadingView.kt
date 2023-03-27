package com.example.generadorcurp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingView(message: String){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = message , fontSize = 26.sp, modifier = Modifier.padding(15.dp))

        CircularProgressIndicator(modifier = Modifier.size(50.dp))
    }
}