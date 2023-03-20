package com.rfcpractica.form.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.Size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.rfcpractica.R
import com.rfcpractica.components.EmptyView
import com.rfcpractica.components.ErrorView
import com.rfcpractica.components.LoadingView
import com.rfcpractica.form.ui.componentes.CustomInput
import com.rfcpractica.form.ui.componentes.DatePickerDate
import com.rfcpractica.form.ui.componentes.DropdownStates
import com.rfcpractica.form.ui.componentes.RadioButtonGroupSex

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen(viewModel: FormViewModel, navigationController: NavHostController) {
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
            LazyColumn(
                //modifier = Modifier.verticalScroll(rememberScrollState())
            ) {item{
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
                        .padding(8.dp),
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


            }}
        },
        topBar = {
            TopAppBar(
                title = { Text("CONSULTS DE CURP", color = Color.White) },
                backgroundColor = Color(0xFF9532F8)
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("GENERAR") },
                onClick = { viewModel.generateCURP() },
                backgroundColor = Color(0xFF9532F8),
                contentColor = Color(0xFF000000)
            )
        }
    )
}