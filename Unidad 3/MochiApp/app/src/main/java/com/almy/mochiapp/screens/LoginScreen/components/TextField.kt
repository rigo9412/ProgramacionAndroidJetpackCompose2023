package com.almy.mochiapp.screens.LoginScreen.components


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
import com.almy.mochiapp.ui.theme.*
import com.almy.mochiapp.R

@Composable
fun CustomInput(placeHolder:String, text :String,modifier: Modifier, OnTextFieldChangued:(String) -> Unit) {
    TextField(
        value = text,
        onValueChange = {OnTextFieldChangued(it)},
        placeholder = { Text(text = placeHolder,textAlign = TextAlign.Center, color = WhiteSmoke ) },
        visualTransformation = if(placeHolder == stringResource(id = R.string.login3)) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier,

        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = InputPurpleColor,
            focusedIndicatorColor = WhiteSmoke,
            unfocusedIndicatorColor = WhiteSmoke

        )
    )
}
