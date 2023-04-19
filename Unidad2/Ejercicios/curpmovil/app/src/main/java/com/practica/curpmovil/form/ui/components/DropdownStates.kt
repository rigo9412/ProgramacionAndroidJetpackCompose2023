package com.practica.curpmovil.form.ui.components

import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownStates(
    modifier: Modifier,
    selected: Pair<String, String>,
    label: String,
    listItems: List<Pair<String, String>>,
    onValueChange: (Pair<String, String>) -> Unit
) {
    var open by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = open, onExpandedChange = { open = it }) {
        TextField(
            value = selected.second,
            onValueChange = { onValueChange(selected) },
            trailingIcon = { TrailingIcon(expanded = open) },
            modifier = modifier, colors = ExposedDropdownMenuDefaults.textFieldColors(),
            readOnly = true
        )
        if (!listItems.isEmpty()) {
            ExposedDropdownMenu(expanded = open, onDismissRequest = { open = false }) {
                listItems.forEach { selectionOption ->
                    DropdownMenuItem(onClick = {
                        onValueChange(selectionOption)
                        open = false
                    }) {
                        Text(text = selectionOption.second)
                    }
                }
            }
        }
    }
}