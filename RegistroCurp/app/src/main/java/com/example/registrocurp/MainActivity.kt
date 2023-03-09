package com.example.registrocurp

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.registrocurp.domain.sexos
import com.example.registrocurp.ui.theme.RegistroCurpTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroCurpTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ForScreen()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForScreen(){
    var name by remember { mutableStateOf("") }
    var name1 by remember { mutableStateOf("") }
    var name2 by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var fechaN by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState()))
    {
         CustomInput(
             label = stringResource(R.string.name),
             value = name,
             onChangeValue = { name = it},
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(10.dp),
             focusManager = focusManager
         )
        CustomInput(
            label = "Apellido Paterno",
            value = name1,
            onChangeValue = { name1 = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
        CustomInput(
            label = "Apellido Materno",
            value = name2,
            onChangeValue = { name2 = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
        RadioButtonGroupSex(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            items = sexos,
            selection = sexo,
            onItemClick = {sexo = it}
        )
        DatePickerDate(
            label = "Fecha de nacimiento",
            value = fechaN,
            onValueChange = { fechaN = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
    }
}

@Composable
fun CustomInput(
    label: String,
    value:String,
    onChangeValue: (String) -> Unit,
    modifier: Modifier,
    focusManager: FocusManager
){
    TextField(
        value = value,
        onValueChange = onChangeValue,
        modifier = modifier,
        label = { Text(text = label)},
        isError = false,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Characters,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        )
        )
}

@Composable
fun RadioButtonGroupSex(
    modifier: Modifier,
    items: List<Pair<String, String>>,
    selection: String,
    onItemClick: ((String) -> Unit)
) {
    Column(modifier = modifier) {
        items.forEach { item ->
            RadioButtonLabel(
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerDate(
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    pattern: String = "yyyy-MM-dd",
    modifier: Modifier,
    focusManager: FocusManager
){
    val formatter= DateTimeFormatter.ofPattern(pattern)
    val date = if(value.isNotBlank()) LocalDate.parse(value,formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _,year,month,dayOfMonth ->
            onValueChange(LocalDate.of(year,month+1, dayOfMonth).toString())
        },
        date.year,
        date.monthValue -1,
        date.dayOfMonth,
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled = false,
        readOnly = true,
        modifier = Modifier.clickable { dialog.show() }.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        label = {Text(label)},
        textStyle = TextStyle(color = Color.Black, fontSize = 17.sp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RegistroCurpTheme {
        ForScreen()
    }
}