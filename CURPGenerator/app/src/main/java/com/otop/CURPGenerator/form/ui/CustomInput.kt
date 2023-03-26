package com.otop.CURPGenerator.form.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

@SuppressLint("UnrememberedMutableState")
@Composable
fun CustomInput(modifier: Modifier,label: String, value: String, onChangeValue: (String) -> Unit, focusManager: FocusManager) {
    TextField(
        label = {Text(text = label)}
        , value = value
        , onValueChange = onChangeValue
        , maxLines = 1
        , singleLine = true
        , isError = false
        , keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Characters ,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        modifier = Modifier.fillMaxWidth()
    )
}