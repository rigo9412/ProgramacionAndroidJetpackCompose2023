package com.example.generadorcurp.ui.form.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

import com.example.generadorcurp.GlobalProvider
import com.example.generadorcurp.R
import com.example.generadorcurp.components.*
import com.example.generadorcurp.form.domain.*
import com.example.generadorcurp.form.domain.ui.components.*
import com.example.generadorcurp.ui.nav.Screens

@Composable
fun FormScreen() {
    val viewModel = GlobalProvider.current.formVM
    val state = viewModel.uiState.collectAsState().value
    val data = viewModel.uiStateData.collectAsState().value
    val navigationController = GlobalProvider.current.nav

    when (state) {
        is CurpFormScreenState.Error -> ErrorView(message = state.message) {
            viewModel.onEvent(CurpFormEvent.Hide)
        }
        is CurpFormScreenState.Loaded -> Form(data){
            viewModel.onEvent(it)
        }
        is CurpFormScreenState.Loading -> LoadingView(message = state.message)
        is CurpFormScreenState.Done -> CurpView(action = {
            navigationController.navigate(
                route = Screens.Result.generateRoute(
                    state.curp,
                    state.name
                )
            ) {
                popUpTo(navigationController.graph.id) {
                    inclusive = true
                }
            }
        })
        CurpFormScreenState.Init -> viewModel.initState()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Form(data: CurpFormModelState, onEvent: (CurpFormEvent) -> Unit) {
    val focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    val navigationController = GlobalProvider.current.nav
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                CustomTextField(
                    label = stringResource(R.string.nombre),
                    value = data.nombre,
                    error = data.nombreError,
                    onChangeValue = { onEvent(CurpFormEvent.NombreChanged(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
                CustomTextField(
                    label = stringResource(R.string.pApellido),
                    value = data.paterno,
                    error = data.paternoError,
                    onChangeValue = {
                        onEvent(CurpFormEvent.PaternoChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
                CustomTextField(
                    label = stringResource(R.string.sApellido),
                    value = data.materno,
                    error = data.maternoError,
                    onChangeValue = {
                        onEvent(CurpFormEvent.MaternoChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )
                DatePick(
                    label = stringResource(R.string.fecha),
                    value = data.fechaNacimiento,
                    onValueChange = {
                        onEvent(CurpFormEvent.FechaChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )

                RadioButtonPairGroup(
                    label = stringResource(R.string.sexo),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    items = data.generoList,
                    selection = data.genero.first,
                    onItemClick = {
                        onEvent(CurpFormEvent.GeneroChanged(it))
                    }

                )
                PairListDropdown(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    selected = data.estado,
                    label = stringResource(R.string.estado),
                    listItems = data.estadoList,
                    onValueChange = {

                        onEvent(CurpFormEvent.EstadoChanged(it))
                    }

                )


            }
        },
        topBar = {
            TopAppBar(

                title = { Text("TITULO", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = {

                        navigationController.navigate(route = Screens.HomeScreen.route) {
                            popUpTo(navigationController.graph.id) {
                                inclusive = true
                            }
                        }

                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "atras"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(CurpFormEvent.Submit)
            }) {
                Icon(Icons.Filled.Done, contentDescription = "SUBMIT")
            }
        }
    )
}

//@Composable
//fun FormScreen(viewModel : FormViewModel, navigationController : NavHostController){
//    val estado = viewModel.uiEstado.collectAsState().value
//    Log.d("ESTADO",estado.toString())
//
//    when(estado){
//        is FormUIestado.Error -> ErrorView(message = estado.message){viewModel.initState()}
//        is FormUIestado.Loaded -> Formulario(viewModel)
//        is FormUIestado.Loading -> LoadingView(message = estado.message)
//        is FormUIestado.Done -> CurpView(action = {Log.d("CURP",estado.curp);navigationController.navigate("result/${estado.curp}")})
//        else -> {}
//    }
//}
//
//@Composable
//fun Formulario(viewModel: FormViewModel) {
//    val data = viewModel.uiEstadoData.collectAsState().value
//    val focusManager = LocalFocusManager.current
//    Column(modifier = Modifier.padding(15.dp)){
//        Text(text = "Generador de CURP", fontSize = 28.sp)
//
//        textInput(label = stringResource(id = R.string.nombre), nombre = data.nombre, modifier = Modifier,
//            onTextFieldChange = {viewModel.onNameChange(it, data.primerApellido, data.segundoApellido)}, focusManager = focusManager)
//        textInput(label = stringResource(id = R.string.pApellido), nombre = data.primerApellido, modifier = Modifier,
//            onTextFieldChange = {viewModel.onNameChange(data.nombre, it, data.segundoApellido)}, focusManager = focusManager)
//        textInput(label = stringResource(id = R.string.sApellido), nombre = data.segundoApellido, modifier = Modifier,
//            onTextFieldChange = {viewModel.onNameChange(data.nombre, data.primerApellido, it)}, focusManager = focusManager)
//
//        RadioButtonPairListGroup(label = stringResource(id = R.string.sexo), modifier = Modifier, items = generos,
//            selection = data.genero, onItemClick = {viewModel.onChangeGenero(it)})
//
//        DatePicker(label = stringResource(id = R.string.fecha), value = data.fechaNacimiento.toString(),
//            onValueChange = {viewModel.onChangeFechaNacimiento(LocalDate.parse(it, FORMATTER_INPUT))},
//            modifier = Modifier, focusManager = focusManager)
//
//        PairListDropdown(modifier = Modifier, selected = data.estado, label = stringResource(id = R.string.estado),
//            listItems = estadosMexicanos, onValueChange = {viewModel.onChangeEstado(it)})
//
//        btnEnter(enabled = data.btnEnabled, generar = { viewModel.generarCURP() }, content = "Generar CURP")
//    }
//}
