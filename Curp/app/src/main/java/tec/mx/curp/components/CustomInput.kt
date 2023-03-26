package tec.mx.curp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomImput(
    label: String,
    value: String,
    error: String?,
    onChangeValue: (String) -> Unit,
    modifier: Modifier,
    onAction: () -> Unit,
    imeAction: ImeAction = ImeAction.Next
){
    val isError = error != null && error != ""
    Column() {
        OutlinedTextField(
            value = value,
            onValueChange = onChangeValue,
            modifier = modifier,

            label = {Text(label)},
            isError = isError,
            maxLines = 1,
            singleLine = true,

            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                imeAction = imeAction,
                keyboardType = KeyboardType.Text
            ),

            keyboardActions = KeyboardActions(
                onNext = {
                    onAction()
                },
                onDone = {
                    onAction()
                }
            ),
        )

        if (isError){
            Text(
                error ?: "",
                color = Color.Red,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }
}