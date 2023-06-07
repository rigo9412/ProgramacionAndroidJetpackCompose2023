package com.almy.gato.components


import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign


@Composable
fun CustomInput(text :String,modifier: Modifier, OnTextFieldChangued:(String) -> Unit) {
    TextField(
        value = text,
        onValueChange = {OnTextFieldChangued(it)},
        modifier = modifier,
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = Color.Black,
        )
    )
}
