package com.example.formulario.ui.theme

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownStates(
    modifier: Modifier,
    selected: Pair<String,String>,
    label: String,
    listItems: List<Pair<String,String>>,
    onValueChange: (Pair<String,String>) -> Unit
){
    var open by remember{ mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded =open, onExpandedChange ={open=it}) {
        TextField(
            value = selected.second,
            onValueChange = { onValueChange(selected) },
            modifier = modifier,
            trailingIcon = { TrailingIcon(expanded = open)},
            label = {Text(label)},

            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            readOnly = true
        )

        if(listItems.isNotEmpty()){
            ExposedDropdownMenu(expanded = open, onDismissRequest = { open = false }) {
                listItems.forEach{selectionOption ->
                    DropdownMenuItem(onClick = { onValueChange(selectionOption)
                        open=false}) {
                            Text(text=selectionOption.second)
                    }
                }
            }
        }
    }
}