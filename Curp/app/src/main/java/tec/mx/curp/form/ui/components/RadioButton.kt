package tec.mx.curp.form.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonGroupSex(
    modifier: Modifier,
    items: List<Pair<String,String>>,
    selection: String,
    onItemClick: ((Pair<String,String>) -> Unit)
) {
    Column(modifier = modifier) {
        items.forEach { item ->
            RadioButtonLabel(
                modifier = Modifier.fillMaxWidth().clickable {  onItemClick(item) },
                label = item.second,
                selected = item.first == selection,
                onClick = {
                    onItemClick(item)
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
    onClick: (() -> Unit)?,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors()
) {

    Row(
        modifier = modifier
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

