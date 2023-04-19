package com.practica.curpmovil.form.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction

@Composable
fun CustomInput(
    label: String,
    value: String,
    onChangeValue: (String) -> Unit,
    modifier: Modifier,
    focusManager: FocusManager
) {
    TextField(
        value = value,
        onValueChange = onChangeValue,
        modifier = modifier,

        label = {Text(label)},
        isError = false,
        maxLines = 1,
        singleLine = true,
        keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)}),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
    )
}
