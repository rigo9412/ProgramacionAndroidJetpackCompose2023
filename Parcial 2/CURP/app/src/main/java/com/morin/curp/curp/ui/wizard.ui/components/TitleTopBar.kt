package com.morin.curp.curp.ui.wizard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
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

@Composable
fun TitleTopBar(title: String, subTitle: String?, isFirst: Boolean, onBackAction: () -> Unit) {
    val logo: Painter = painterResource(id = R.drawable.logo)
    TopAppBar(
        modifier = Modifier.height(120.dp),
    ){
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                if (isFirst) {
                    IconButton(
                        onClick = { onBackAction() }
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Cerrar"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { onBackAction() }
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
                Box(modifier = Modifier.padding(top= 10.dp,end = 10.dp)){
                    Image(
                        painter = logo,
                        contentDescription = "logo",
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 8.dp
                )
            )
            Text(
                text = subTitle ?: "",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}