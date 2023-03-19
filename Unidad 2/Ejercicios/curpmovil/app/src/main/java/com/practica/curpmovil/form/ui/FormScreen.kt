package com.practica.curpmovil.form.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.practica.curpmovil.form.domain.sexos
import com.practica.curpmovil.form.ui.components.CustomInput
import com.practica.curpmovil.form.ui.components.DatePickerBirthDate
import com.practica.curpmovil.form.ui.components.DropdownStates


import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.practica.curpmovil.form.ui.components.RadioButtonGroupSex


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormaCurp(viewModel: FormViewModel){

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Form(viewModel: FormViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    var focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    var name by remember { mutableStateOf("") }
    var name1 by remember {
        mutableStateOf("")
    }
    var name2 by remember {
        mutableStateOf("")
    }
    var fecha by remember {
        mutableStateOf("")
    }
    var sexo by remember {
        mutableStateOf(Pair<String,String>("",""))
    }
    var state by remember {
        mutableStateOf(Pair<String,String>("",""))
    }
    var expanded by remember {
        mutableStateOf("")
    }

    Scaffold(scaffoldState = scaffoldState, content = { Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        CustomInput(
            label = "Nombre(s)",
            value = name,
            onChangeValue = { name = it; viewModel.onChangeName(name) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
        CustomInput(
            label = "Apellido Paterno",
            value = name1,
            onChangeValue = { name1 = it;viewModel.onChangeMiddleName(name1)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
        CustomInput(
            label = "Apellido Materno",
            value = name2,
            onChangeValue = { name2 = it;viewModel.onChangeLastName(name2) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
        RadioButtonGroupSex(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), items = sexos, selection = sexo, onItemClick = { sexo = it;viewModel.onChangeGender(sexo) })

        DatePickerBirthDate(
            label = "Fecha de Nacimiento",
            value = fecha,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager,
            onValueChange = { fecha =it;viewModel.onChangeBirth(fecha) })
        DropdownStates(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            selected = data.state,
            label = "Estado",
            listItems = data.statesList,
            onValueChange = { state = it;viewModel.onChangeState(state) }
        )
    }}, topBar = {
        TopAppBar(title = { Text(text = "Generar Curp")})

    }, floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.generateCurp() }) {
            Icon(Icons.Filled.Done, contentDescription = "Generar CURP")
        }
    }
    )
}

data class CurpUIModel(
    val isValid: Boolean = false,
    var curp: String = "",
    val name: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val birth: String = "",
    val gender: Pair<String, String> = Pair<String, String>("", ""),
    val state: Pair<String, String> = Pair<String, String>("", ""),
    val sexList: ArrayList<Pair<String, String>> = ArrayList(),
    val statesList: ArrayList<Pair<String, String>> = ArrayList()
)


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen(viewModel: FormViewModel) {
    val state = viewModel.uiState.collectAsState().value
    when (state) {
        FormViewModel.FormUiState.Empty -> Text(text = "vicio")
        is FormViewModel.FormUiState.Error -> Text(text = "error")
        FormViewModel.FormUiState.Loaded -> Form(viewModel)
        is FormViewModel.FormUiState.Loading -> Text(text = "cargando")
        is FormViewModel.FormUiState.Success -> Text(text = viewModel.getCurp())
    }
}