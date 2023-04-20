package com.morin.curp.curp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morin.curp.R
import com.morin.curp.ui.theme.green
import com.morin.curp.ui.theme.greenDark

@Composable
fun Header(title: String,subtitle:String) {
    val logo: Painter = painterResource(id = R.drawable.logo)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(green)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(greenDark)
                .padding(8.dp)
        ) {
            Image(
                painter = logo,
                contentDescription = "logo",
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = title,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = subtitle,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}