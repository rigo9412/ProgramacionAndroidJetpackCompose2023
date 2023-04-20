package com.lanazirot.curpavanzado.screens.components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lanazirot.curpavanzado.R
import com.lanazirot.curpavanzado.domain.enums.State


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomInputDropdownStates(
    value: State = State.values()[0],
    onValueChange: (State) -> Unit = {},
    values: List<State> = State.values().toList(),
    label: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(values[0]) }
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
            label = { Text(label) },
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
            values.forEach { state ->
                DropdownMenuItem(
                    modifier = modifier.testTag("manual_mode_state_${state.stateName}"),
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
