package com.program.nuevocurp.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.program.nuevocurp.R
import com.program.nuevocurp.ui.theme.green
import com.program.nuevocurp.ui.theme.greenDark


@Composable
@Preview
fun Footer() {
    val footer: Painter = painterResource(id = R.drawable.footer)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(green)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(greenDark)
        ) {
            Image(
                painter = footer,
                contentDescription = "footer",
                contentScale = ContentScale.Crop,
            )
        }


    }

}