package com.example.generadorcurp.form.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.generadorcurp.R
import com.example.generadorcurp.form.ui.components.*
import com.example.generadorcurp.components.*
import com.example.generadorcurp.form.domain.*
import java.time.LocalDate

@Composable
fun FormScreen(viewModel : FormViewModel, navigationController : NavHostController){
    val estado = viewModel.uiEstado.collectAsState().value
    Log.d("ESTADO",estado.toString())

    when(estado){
        is FormUIestado.Error -> ErrorView(message = estado.message){viewModel.initState()}
        is FormUIestado.Loaded -> Formulario(viewModel)
        is FormUIestado.Loading -> LoadingView(message = estado.message)
        is FormUIestado.Done -> CurpView(action = {Log.d("CURP",estado.curp);navigationController.navigate("result/${estado.curp}")})
        else -> {}
    }
}

@Composable
fun Formulario(viewModel: FormViewModel) {
    val data = viewModel.uiEstadoData.collectAsState().value
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier.padding(15.dp)){
        Text(text = "Generador de CURP", fontSize = 28.sp)

        textInput(label = stringResource(id = R.string.nombre), nombre = data.nombre, modifier = Modifier,
            onTextFieldChange = {viewModel.onNameChange(it, data.primerApellido, data.segundoApellido)}, focusManager = focusManager)
        textInput(label = stringResource(id = R.string.pApellido), nombre = data.primerApellido, modifier = Modifier,
            onTextFieldChange = {viewModel.onNameChange(data.nombre, it, data.segundoApellido)}, focusManager = focusManager)
        textInput(label = stringResource(id = R.string.sApellido), nombre = data.segundoApellido, modifier = Modifier,
            onTextFieldChange = {viewModel.onNameChange(data.nombre, data.primerApellido, it)}, focusManager = focusManager)

        RadioButtonPairListGroup(label = stringResource(id = R.string.sexo), modifier = Modifier, items = generos,
            selection = data.genero, onItemClick = {viewModel.onChangeGenero(it)})

        DatePicker(label = stringResource(id = R.string.fecha), value = data.fechaNacimiento.toString(),
            onValueChange = {viewModel.onChangeFechaNacimiento(LocalDate.parse(it, FORMATTER_INPUT))},
            modifier = Modifier, focusManager = focusManager)

        PairListDropdown(modifier = Modifier, selected = data.estado, label = stringResource(id = R.string.estado),
            listItems = estadosMexicanos, onValueChange = {viewModel.onChangeEstado(it)})

        btnEnter(enabled = data.btnEnabled, generar = { viewModel.generarCURP() }, content = "Generar CURP")
    }
}
