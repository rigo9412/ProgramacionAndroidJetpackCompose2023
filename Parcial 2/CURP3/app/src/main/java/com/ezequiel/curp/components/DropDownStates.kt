package com.ezequiel.curp.form.ui

import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownStates (
    modifier: Modifier,
    selected: Pair<String,String>,
    label: String,
    listItems: List<Pair<String, String>>,
    onValueChange: (Pair<String,String>) -> Unit){
    var open by remember{ mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = open,
        onExpandedChange = {open = it}
    ) {
        TextField(
            value = selected.second,
            modifier = modifier,
            label = { Text(text = label)},
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
        val filteringOptions = listItems.filter { it.first.contains(selected.first,ignoreCase = false) }

        if(filteringOptions.isNotEmpty()){

            ExposedDropdownMenu(
                expanded = open,
                onDismissRequest = { open = false }) {
                filteringOptions.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(selectedOption)
                            open = false
                        }) {
                        Text(text = selectedOption.second)
                    }
                }
            }
        }
    }
}