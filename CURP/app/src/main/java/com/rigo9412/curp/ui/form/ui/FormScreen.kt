package com.rigo9412.curp.ui.form.ui

import DatePickerBirthDate
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
import androidx.navigation.NavHostController
import com.example.curp.R
import com.rigo9412.curp.GlobalProvider
import com.rigo9412.curp.components.EmptyView
import com.rigo9412.curp.components.ErrorView
import com.rigo9412.curp.components.LoadingView
import com.rigo9412.curp.form.ui.CustomInput
import com.rigo9412.curp.form.ui.DropdownStates
import com.rigo9412.curp.form.ui.RadioButtonGroupSex

import com.rigo9412.curp.ui.nav.Screens


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
                    label = stringResource(R.string.name),
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
                    label = stringResource(R.string.middle_name),
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
                    label = stringResource(R.string.lastname),
                    value = data.lastName,
                    error = data.lastNameError,
                    onChangeValue = {
                        onEvent(CurpFormEvent.LastNameChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Exit)
                    }
                )
                DatePickerBirthDate(
                    label = stringResource(R.string.birth),
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
                DropdownStates(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    selected = data.state,
                    label = stringResource(R.string.state),
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


