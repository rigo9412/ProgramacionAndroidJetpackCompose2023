package com.example.registrocurp.form.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.registrocurp.R
import com.example.registrocurp.components.EmptyView
import com.example.registrocurp.components.ErrorView
import com.example.registrocurp.components.LoadingView
import com.example.registrocurp.form.ui.componentes.CustomInput
import com.example.registrocurp.form.ui.componentes.DatePickerDate
import com.example.registrocurp.form.ui.componentes.DropdownStates
import com.example.registrocurp.form.ui.componentes.RadioButtonGroupSex
import com.example.registrocurp.result.ResultView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen(viewModel: FormViewModel,navigationController: NavHostController) {
    val state = viewModel.uiState.collectAsState().value

    when (state) {
        is FormUiState.Error -> ErrorView(error = state.message) { viewModel.initState() }
        is FormUiState.Loaded -> Form(viewModel)
        is FormUiState.Loading -> LoadingView(message = state.message)
        is FormUiState.Done -> EmptyView(action = {
            navigationController.navigate("result/${state.curp}/${state.name}}")
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
                    onChangeValue = { viewModel.onChangeName(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
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
                DatePickerDate(
                    label = "Fecha de nacimiento",
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


            }
        },
        topBar = {
            TopAppBar(
                title = { Text("GENERADOR DE CURP", color = Color.White) },
                backgroundColor = Color.Blue
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("GENERAR CURP") },
                onClick = { viewModel.generateCURP() },
                backgroundColor = Color(0xFFBBDEFB),
                contentColor = Color(0xFF0D47A1)
            )
        }
    )
}