package com.practica.curpmovil.form.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonLabel(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: (() -> Unit)?,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors()
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            colors = colors
        )
        Text(
            text = label,
            style = MaterialTheme.typography.body1.merge(),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun RadioButtonGroupSex(
    modifier: Modifier,
    items: List<Pair<String, String>>,
    selection: Pair<String,String>,
    onItemClick: ((Pair<String,String>) -> Unit)
) {
    Column(modifier = modifier) {
        items.forEach { item ->
            RadioButtonLabel(
                label = item.second,
                selected = item.first == selection.first,
                onClick = { onItemClick(item) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) })
        }
    }
}