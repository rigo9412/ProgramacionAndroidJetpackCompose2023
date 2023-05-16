package com.otop.poketest.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.otop.poketest.MainViewModel
import org.json.JSONObject


@Composable
fun ComboBox(dictionary: Map<String, String>,viewModel: MainViewModel) {


    val entries = dictionary.entries.toList()
    var expanded by remember { mutableStateOf(false) }
    var selectedValue by remember { mutableStateOf("") }


    Box(modifier = Modifier
        .wrapContentSize()
        .clickable(onClick = { expanded = true })
    ) {
        Text(
            text = selectedValue.ifEmpty { "Seleccione un país" },
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            fontSize = 30.sp
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            entries.forEach { (key, value) ->
                DropdownMenuItem(onClick = {
                    viewModel.countryTop.value = key
                    selectedValue = value + key
                    expanded = false
                }) {
                    Text(text = key)
                    Text(text = value)
                }
            }
        }
    }
}
