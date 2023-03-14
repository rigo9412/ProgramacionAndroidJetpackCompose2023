package com.lanazirot.curpgenerator.screens.curp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CustomInput(
    value: String,
    label: String,
    focusManager: FocusManager,
    numeric: Boolean = false,
    maxLines: Int = 1,
    lastInput: Boolean = false,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    isError : Boolean = false,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            capitalization = capitalization,
            keyboardType = if (numeric) KeyboardType.Number else KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        isError = isError,
        singleLine = maxLines == 1,
        keyboardActions = KeyboardActions(
            onDone = {
                if (!lastInput) {
                    focusManager.moveFocus(FocusDirection.Down)
                } else {
                    focusManager.clearFocus()
                }
            }
        )
    )
}
