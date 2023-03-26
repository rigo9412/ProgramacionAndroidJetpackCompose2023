package com.otop.CURPGenerator.form.ui

import android.widget.RadioButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RadioButtonGroupSex(
    modifier: Modifier,
    items: List<Pair<String, String>>,
    selection: String,
    onItemClick: ((Pair<String,String>) -> Unit)
) {
    Column(modifier = Modifier) {
        items.forEach { item ->
            RadioButtonLabel(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) },
                label = item.second,
                selected = item.first == selection,
                onClick = { onItemClick(item) },
                enabled = true
            )
        }
    }
}

@Composable
fun RadioButtonLabel(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick, enabled = enabled)
        Text(
            text = label,
            style = MaterialTheme.typography.body1.merge(),
            modifier = Modifier.padding(start = 16.dp)
        )
    }

}