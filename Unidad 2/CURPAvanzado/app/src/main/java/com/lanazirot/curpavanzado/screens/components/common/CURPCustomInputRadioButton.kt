package com.lanazirot.curpavanzado.screens.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun CustomInputRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun <T> CustomInputRadioButtonGroup(
    modifier: Modifier = Modifier,
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit
) {
    var selectedOption by remember { mutableStateOf(selectedOption) }
    Column(modifier = modifier) {
        options.forEach { text ->
            CustomInputRadioButton(
                text = text.toString(),
                modifier = Modifier.testTag("manual_mode_gender_${selectedOption.toString()}"),
                selected = text.toString() == selectedOption.toString(),
                onClick = {
                    selectedOption = text
                    onOptionSelected(text)
                }
            )
        }
    }
}