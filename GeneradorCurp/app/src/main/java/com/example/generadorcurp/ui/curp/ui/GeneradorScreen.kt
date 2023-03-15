package com.example.generadorcurp.ui.curp.ui

import android.app.DatePickerDialog
import android.provider.Settings.Global.getString
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.generadorcurp.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun generadorCurpScreen(viewModel: CURPViewModel){
    generadorView(viewModel = viewModel)
}

@Composable
fun generadorView(viewModel: CURPViewModel){
    val nombre : String by viewModel.nombre.observeAsState("")
    val pApellido : String by viewModel.primerApellido.observeAsState("")
    val sApellido : String by viewModel.segundoApellido.observeAsState("")
    val estado : String by viewModel.estado.observeAsState("")
    val sexo : String by viewModel.sexo.observeAsState("")
    val fecha : LocalDate by viewModel.fecha.observeAsState(LocalDate.of(2018, 12, 31))


    Column(modifier = Modifier){
        nombreInput(nombre = nombre, modifier = Modifier, onTextFieldChanged = {viewModel.onCurpChange(it,pApellido,sApellido,estado,sexo,fecha)})
        primerApellido(pApellido = pApellido, modifier = Modifier, onTextFieldChanged = {viewModel.onCurpChange(nombre,it,sApellido,estado,sexo,fecha)})
        segundoApellido(sApellido = sApellido, modifier = Modifier, onTextFieldChanged = {viewModel.onCurpChange(nombre,pApellido,it,estado,sexo,fecha)})
        sexo(modifier = Modifier, sexos,sexo, onItemClick = {viewModel.onCurpChange(nombre,pApellido,sApellido,estado,sexo,fecha)})
        fechaNacimiento(label = "fecha", value = fecha.toString(), modifier = Modifier, focusManager = )
    }
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
                    pattern: String = "yyyy-mm-dd",
                    value : String,
                    onValueChange: (String) -> Unit ={},
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

    TextField(value = value, onValueChange = onValueChange,
                enabled = false,

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

@Composable
fun estado(){
    Text(text= stringResource(R.string.estado) +":")



}

val sexos = listOf(Pair("Hombre","H"),Pair("Mujer","M"),Pair("No binario","X"))

val estadosMexicanos = listOf(
    "Aguascalientes" to "AS",
    "Baja California" to "BC",
    "Baja California Sur" to "BS",
    "Campeche" to "CC",
    "Coahuila" to "CL",
    "Colima" to "CM",
    "Chiapas" to "CS",
    "Chihuahua" to "CH",
    "Ciudad de México" to "DF",
    "Durango" to "DG",
    "Guanajuato" to "GT",
    "Guerrero" to "GR",
    "Hidalgo" to "HG",
    "Jalisco" to "JC",
    "Estado de México" to "MC",
    "Michoacán" to "MN",
    "Morelos" to "MS",
    "Nayarit" to "NT",
    "Nuevo León" to "NL",
    "Oaxaca" to "OC",
    "Puebla" to "PL",
    "Querétaro" to "QT",
    "Quintana Roo" to "QR",
    "San Luis Potosí" to "SP",
    "Sinaloa" to "SL",
    "Sonora" to "SR",
    "Tabasco" to "TC",
    "Tamaulipas" to "TS",
    "Tlaxcala" to "TL",
    "Veracruz" to "VZ",
    "Yucatán" to "YN",
    "Zacatecas" to "ZS"
)

