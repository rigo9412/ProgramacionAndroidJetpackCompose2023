package com.example.registrocurp.curp.ui.form.ui

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
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.registrocurp.GlobalProvider
import com.example.registrocurp.R
import com.example.registrocurp.curp.components.*
import com.example.registrocurp.curp.ui.nav.Screens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen() {
    val viewModel = GlobalProvider.current.formVM
    val state = viewModel.uiState.collectAsState().value
    val navigationController = GlobalProvider.current.nav

    when (state) {
        is FormCurpScreenState.Error -> ErrorView(error = state.message) { viewModel.initState() }
        is FormCurpScreenState.Loaded -> Form(viewModel)
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
        else -> {

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
                    error = data.nameError,
                    onChangeValue = { viewModel.onEvent(CurpFormEvent.NameChanged(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .testTag("Name"),
                    focusManager = focusManager
                )
                CustomInput(
                    label = stringResource(R.string.middle_name),
                    value = data.middleName,
                    error = data.middleNameError,
                    onChangeValue = {
                        viewModel.onEvent(CurpFormEvent.MiddleNameChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .testTag("Middle_name"),
                    focusManager = focusManager
                )
                CustomInput(
                    label = stringResource(R.string.lastname),
                    value = data.lastName,
                    error = data.lastNameError,
                    onChangeValue = {
                        viewModel.onEvent(CurpFormEvent.LastNameChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .testTag("Lastname"),
                    focusManager = focusManager
                )
                DatePickerDate(
                    label = stringResource(R.string.birth),
                    value = data.birth,
                    onValueChange = {
                        viewModel.onEvent(CurpFormEvent.BirthChanged(it))
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
                        viewModel.onEvent(CurpFormEvent.GenderChanged(it))
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

                        viewModel.onEvent(CurpFormEvent.StateChanged(it))
                    }

                )
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.form_title), color = Color.White) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(CurpFormEvent.Submit)
            }) {
                Icon(Icons.Filled.Done, contentDescription = stringResource(R.string.form_submit))
            }
        }
    )
}


