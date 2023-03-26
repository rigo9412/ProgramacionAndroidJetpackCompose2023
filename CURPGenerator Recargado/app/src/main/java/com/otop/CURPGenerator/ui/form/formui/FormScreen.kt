package com.otop.CURPGenerator.ui.form.formui

import LoadingView
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.otop.CURPGenerator.GlobalProvider
import com.otop.CURPGenerator.ui.form.nav.Screens
import com.otop.CURPGenerator.ui.form.ui.*
import com.otop.CURPGenerator.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen() {
    val viewModel = GlobalProvider.current.formVM
    val state = viewModel.uiState.collectAsState().value
    val data = viewModel.uiStateData.collectAsState().value
    val navigationController = GlobalProvider.current.nav

    when (state) {
        is FormCurpScreenState.Error -> ErrorView(error = state.message) {
            viewModel.onEvent(CurpFormEvent.Hide)
        }
        is FormCurpScreenState.Loaded -> Form(data){
            viewModel.onEvent(it)
        }
        is FormCurpScreenState.Loading -> LoadingView(message = state.message)
        is FormCurpScreenState.Done -> EmptyView(action = {
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
        FormCurpScreenState.Init -> viewModel.initState()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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
                CustomInput(
                    label = "Nombre",
                    value = data.name,
                    error = data.nameError,
                    onChangeValue = { onEvent(CurpFormEvent.NameChanged(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
                CustomInput(
                    label = "Apellido Paterno",
                    value = data.middleName,
                    error = data.middleNameError,
                    onChangeValue = {
                        onEvent(CurpFormEvent.MiddleNameChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
                CustomInput(
                    label = "Apellido Materno",
                    value = data.lastName,
                    error = data.lastNameError,
                    onChangeValue = {
                        onEvent(CurpFormEvent.LastNameChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Out)
                    }
                )
                DatePickerBirthDay(
                    label = "Año de nacimiento",
                    value = data.birth,
                    onValueChange = {
                        onEvent(CurpFormEvent.BirthChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )

                RadioButtonGroupSex(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    items = data.sexList,
                    selection = data.gender.first,
                    onItemClick = {
                        onEvent(CurpFormEvent.GenderChanged(it))
                    }

                )
                DropDownStates(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    selected = data.state,
                    label = "Estado",
                    listItems = data.statesList,
                    onValueChange = {

                        onEvent(CurpFormEvent.StateChanged(it))
                    }

                )


            }
        },
        topBar = {
            TopAppBar(

                title = { Text(stringResource(R.string.form_title), color = Color.White) },
                navigationIcon =  {
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
                Icon(Icons.Filled.Done, contentDescription = stringResource(R.string.form_submit))
            }
        }
    )
}