package com.example.generadorcurp.ui.curp.ui

import android.app.DatePickerDialog
import android.provider.Settings.Global.getString
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.generadorcurp.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun generadorCurpScreen(viewModel: CURPViewModel){
    generadorView(viewModel = viewModel)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun generadorView(viewModel: CURPViewModel){
    val focusManager = LocalFocusManager.current
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val nombre : String by viewModel.nombre.observeAsState("")
    val pApellido : String by viewModel.primerApellido.observeAsState("")
    val sApellido : String by viewModel.segundoApellido.observeAsState("")
    val estado : Pair<String,String> by viewModel.estado.observeAsState(Pair("AS","Aguascalientes"))
    val sexo : String by viewModel.sexo.observeAsState("")
    val fecha : LocalDate by viewModel.fecha.observeAsState(LocalDate.now())
    val openDialog : Boolean by viewModel.showCurp.observeAsState(false)

    Column(modifier = Modifier.padding(15.dp)){
        nombreInput(nombre = nombre, modifier = Modifier, onTextFieldChanged = {viewModel.onNameChange(it,pApellido,sApellido)})
        primerApellido(pApellido = pApellido, modifier = Modifier, onTextFieldChanged = {viewModel.onNameChange(nombre,it,sApellido)})
        segundoApellido(sApellido = sApellido, modifier = Modifier, onTextFieldChanged = {viewModel.onNameChange(nombre,pApellido,it)})
        sexo(modifier = Modifier.focusable(), sexos,sexo, onItemClick = {viewModel.onDataChange(estado,it,fecha)})
        fechaNacimiento(label = stringResource(id = R.string.fecha), value = fecha.toString(), onValueChange = {viewModel.onDataChange(estado,sexo,LocalDate.parse(it,formatter))} ,modifier = Modifier.focusable(), focusManager = focusManager)
        estado(modifier = Modifier, selected = estado, label = stringResource(id = R.string.estado), estadosMexicanos, onValueChange = {viewModel.onDataChange(it,sexo,fecha)})
        btnGenerarCurp {viewModel.onShowChange(true)}

        if(openDialog){
            showCurp(CURP = viewModel.generarCurp(), showCurp = openDialog, onShowChange = {viewModel.onShowChange(openDialog)})
        }
    }
}

@Composable
fun btnGenerarCurp(generar:() -> Unit){
    Button(onClick = { generar() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors= ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFF4303),
            disabledBackgroundColor = Color(0xFFF78058),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )) {
        Text(text = "Generar Curp")
    }
}

@Composable
fun showCurp(CURP : String, showCurp : Boolean ,onShowChange: (Boolean) -> Unit){
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            onShowChange(false)
        },
        title = {
            Text(text = "CURP generado")
        },
        text = {
            Text(CURP)
        },
        confirmButton = {
            Button(

                onClick = {
                    onShowChange(false)
                }) {
                Text("This is the Confirm Button")
            }
        },
        dismissButton = {
            Button(

                onClick = {
                    onShowChange(false)
                }) {
                Text("This is the dismiss Button")
            }
        }
    )
}

@Composable
fun nombreInput(nombre: String,modifier : Modifier, onTextFieldChanged: (String) -> Unit){
    Text(text= stringResource(R.string.nombre) +":")
    TextField(
        value = nombre, onValueChange = { onTextFieldChanged(it.uppercase()) },
        placeholder = { Text(text = stringResource(R.string.nombre)) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = {

        }),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun primerApellido(pApellido: String,modifier : Modifier, onTextFieldChanged: (String) -> Unit){
    Text(text=stringResource(R.string.pApellido)+":")
    TextField(
        value = pApellido, onValueChange = { onTextFieldChanged(it.uppercase()) },
        placeholder = { Text(text = stringResource(R.string.pApellido)) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = {

        }),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun segundoApellido(sApellido: String,modifier : Modifier, onTextFieldChanged: (String) -> Unit){
    Text(text= stringResource(R.string.sApellido) +":")
    TextField(
        value = sApellido, onValueChange = { onTextFieldChanged(it.uppercase()) },
        placeholder = { Text(text = stringResource(R.string.sApellido)) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = {

        }),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun fechaNacimiento(label :String,
                    pattern: String = "yyyy-MM-dd",
                    value : String,
                    onValueChange: (String) -> Unit,
                    modifier : Modifier,
                    focusManager : FocusManager){

    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value,formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            onValueChange(selectedDate.toString())
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled = false,
        readOnly = true,
        modifier = Modifier
            .clickable { dialog.show() }
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        label = { Text(label) },
        textStyle = TextStyle(color = Color.Black, fontSize = 17.sp)
    )

}




@Composable
fun sexo(modifier: Modifier,
         items: List<Pair<String, String>>,
         selection: String,
         onItemClick: ((String) -> Unit)){
    Text(text= stringResource(R.string.sexo) +":")
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun estado(modifier: Modifier,
           selected: Pair<String,String>,
           label: String,
           listItems: List<Pair<String,String>>,
           onValueChange: (Pair<String,String>) -> Unit){
    var open by remember{ mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = open,
        onExpandedChange = {
            open = it
        }
    ) {

        TextField(
            modifier = modifier,
            value = selected.second,
            label = { Text(text = label) },
            onValueChange = {
                onValueChange(selected)
            },
            trailingIcon = {
                TrailingIcon(
                    expanded = open
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            readOnly = true
        )

        if (listItems.isNotEmpty()) {

            ExposedDropdownMenu(
                expanded = open,
                onDismissRequest = {
                    open = false
                }
            ) {
                listItems.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(selectionOption)
                            open = false
                        }
                    ) {
                        Text(text = selectionOption.second)
                    }
                }
            }
        }
    }


}

val sexos = listOf(Pair("H","Hombre"),Pair("M","Mujer"),Pair("X","No binario"))

val estadosMexicanos = listOf(
    "AS" to "Aguascalientes",
    "BC" to "Baja California",
    "BS" to "Baja California Sur",
    "CC" to "Campeche",
    "CL" to "Coahuila",
    "CM" to "Colima",
    "CS" to "Chiapas",
    "CH" to "Chihuahua",
    "DF" to "Ciudad de México",
    "DG" to "Durango",
    "GT" to "Guanajuato",
    "GR" to "Guerrero",
    "HG" to "Hidalgo",
    "JC" to "Jalisco",
    "MC" to "Estado de México",
    "MN" to "Michoacán",
    "MS" to "Morelos",
    "NT" to "Nayarit",
    "NL" to "Nuevo León",
    "OC" to "Oaxaca",
    "PL" to "Puebla",
    "QT" to "Querétaro",
    "QR" to "Quintana Roo",
    "SP" to "San Luis Potosí",
    "SL" to "Sinaloa",
    "SR" to "Sonora",
    "TC" to "Tabasco",
    "TS" to "Tamaulipas",
    "TL" to "Tlaxcala",
    "VZ" to "Veracruz",
    "YN" to "Yucatán",
    "ZS" to "Zacatecas"
)

