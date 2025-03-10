package com.rigo9412.curp.form.ui

import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownStates(
    modifier: Modifier,
    selected: Pair<String,String>,
    label: String,
    listItems: List<Pair<String,String>>,
    onValueChange: (Pair<String,String>) -> Unit
) {
    var open by remember{ mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = open,
        onExpandedChange = {
            open = it
        }
    ) {

        TextField(
            modifier = modifier,
            value = selected.second,
            label = { Text(text = label) },
            onValueChange = {
                onValueChange(selected)
            },
            trailingIcon = {
                TrailingIcon(
                    expanded = open
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            readOnly = true
        )

        if (listItems.isNotEmpty()) {

            ExposedDropdownMenu(
                expanded = open,
                onDismissRequest = {
                    open = false
                }
            ) {
                listItems.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(selectionOption)
                            open = false
                        }
                    ) {
                        Text(text = selectionOption.second)
                    }
                }
            }
        }
    }
}