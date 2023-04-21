package com.game.curp.forms.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.data.EmptyGroup.data
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.game.curp.R
import com.game.curp.domain.sexos

val pattern = Regex("[a-zA-z\\s]*")

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen(viewModel: FormViewModel, navigationController: NavHostController) {
    val state = viewModel.uiState.collectAsState().value
    when (state) {
        is FormUiState.Error -> ErrorView(error = state.message) { viewModel.initState() }//Text(text = "error")
        is FormUiState.Loaded -> Form(viewModel)
        is FormUiState.Loading -> LoadingView(message = state.msg) //Text(text = "cargando")
        is FormUiState.Done -> EmptyView(onClick = {
            navigationController.navigate("result/${state.curp}/${state.name}}") //Screens.Result.generateRoute(state.curp, state.name)
        }
        )
        else -> {

        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Form(viewModel: FormViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    val focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                CustomInput(
                    label = stringResource(R.string.name),
                    value = data.name,
                    onChangeValue = { viewModel.onChangeName(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .testTag("txtNombre"),
                    focusManager = focusManager
                )
                CustomInput(
                    label = "Primer Apellido",
                    value = data.middleName,
                    onChangeValue = {
                        viewModel.onChangeMiddlename(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )
                CustomInput(
                    label = "Segundo Apellido",
                    value = data.lastname,
                    onChangeValue = { viewModel.onChangeLastname(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )
                DatePickerBirthDate(
                    label = "Fecha Nacimiento",
                    value = data.birth,
                    onValueChange = { viewModel.onChangeBirth(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager

                )

                RadioButtonGroupSex(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                        //.testTag("radGenero"),
                    items = data.sexList,
                    selection = data.gender.first,
                    onItemClick = { viewModel.onChangeGender(it) }

                )
                DropdownStates(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    selected = data.state,
                    label = "Estado",
                    listItems = data.statesList,
                    onValueChange = {
                        viewModel.onChangeState(it)
                    }

                )

                /*Button(
                    enabled = data.isValid,
                    onClick = { viewModel.generateCURP() }) {

                    Text(text = "GENERAR CURP")
                }*/

                //if (data.curp != "") {
                    Text(text = data.curp, modifier = Modifier.testTag("txtCURP"))
                //}
            }        },
        topBar = {
            TopAppBar(title = { Text("Generador CURP", color = Color.White) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.generateCURP() }, modifier = Modifier.testTag("btnGenerarCURP")) {
                Icon(Icons.Filled.Done, contentDescription = "Generar CURP")
            }
        }
    )
}

@Composable
fun ErrorView(
    error: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            Icons.Default.Report,
            modifier = Modifier.size(80.dp),
            contentDescription = ""
        )
        Text(
            text = error,
            fontSize = 30.sp
        )
        Button(
            onClick = { onClick() }
        ) {
            Text(text = "Volver a cargar")
        }
    }
}


@Composable
fun LoadingView(
    message: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            Icons.Default.HourglassBottom,
            modifier = Modifier.size(100.dp),
            contentDescription = ""
        )
        Text(text = message, fontSize = 25.sp)
    }
}

@Composable
fun EmptyView(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            Icons.Default.Done,
            modifier = Modifier.size(100.dp),
            contentDescription = ""
        )
        Text(
            text = "¡Se generó el CURP con éxito!",
            fontSize = 25.sp
        )
        Button(
            onClick = { onClick() }) {
            Text(text = "Ver CURP")
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResultScreen(
    curp: String,
    name: String,
    navigationController: NavHostController,
    viewModel: FormViewModel
) {
    viewModel.initState()
    ResultView(curp, name) {
        navigationController.navigate("form")
    }
}
@Composable
fun ResultView(
    curp: String,
    name: String,
    onClick: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            Icons.Default.Done,
            modifier = Modifier.size(100.dp),
            contentDescription = "",
            /*tint = Color.Green*/
        )
        Text(
            text = "$name, este es tu CURP :)".replace("}",""),
            fontSize = 25.sp
        )
        Text(
            text = curp,
            fontSize = 25.sp
        )
        OutlinedButton(
            onClick = { onClick() }
        ) {
            Text(text = "Volver a inicio")
        }
    }
}