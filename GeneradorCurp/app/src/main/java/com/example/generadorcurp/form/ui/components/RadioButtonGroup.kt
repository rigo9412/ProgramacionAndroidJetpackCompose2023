package com.example.generadorcurp.form.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.generadorcurp.R

@Composable
fun RadioButtonPairListGroup(label:String,modifier: Modifier,
         items: List<Pair<String, String>>,
         selection: String,
         onItemClick: ((String) -> Unit)){

    Text(text= "$label:")

    Column(modifier = modifier) {
        items.forEach { item ->
            com.example.generadorcurp.ui.curp.ui.RadioButtonLabel(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item.first) },
                label = item.second,
                selected = item.first == selection,
                onClick = {
                    onItemClick(item.first)
                }
            )

        }

    }

}

@Composable
fun RadioButtonLabel(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: (() -> Unit),
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors()
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected =  selected, onClick = onClick, enabled = enabled, colors =  colors)
        Text(text = label, style = MaterialTheme.typography.body1.merge(), modifier = Modifier.padding(start = 16.dp))
    }
}