package com.game.curp.forms.ui

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
){
    OutlinedTextField(
        value = value,
        onValueChange = onChangeValue,
        modifier = modifier,
        label = { Text(label) },
        isError = false,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
}