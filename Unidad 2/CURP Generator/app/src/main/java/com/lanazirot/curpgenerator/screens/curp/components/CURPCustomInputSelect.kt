package com.lanazirot.curpgenerator.screens.curp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lanazirot.curpgenerator.R
import com.lanazirot.curpgenerator.domain.enums.State


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomInputDropdownStates(
    value: State = State.values()[0],
    onValueChange: (State) -> Unit = {}
) {
    val states = State.values().toMutableList()
    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(states[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = value.stateName,
            onValueChange = {
                onValueChange(selectedType)
            },
            label = { Text(stringResource(id = R.string.input_state_user)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = MaterialTheme.colors.primary,
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            states.forEach { state ->
                DropdownMenuItem(
                    onClick = {
                        selectedType = state
                        expanded = false
                        onValueChange(state)
                    }
                ) {
                    Text(state.stateName)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatesPreview() {
    CustomInputDropdownStates()
}