package com.almy.curp.form.ui

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.almy.curp.components.EmptyView
import com.almy.curp.components.ErrorView
import com.almy.curp.components.LoadingView
import com.almy.curp.form.ui.components.CustomInput
import com.almy.curp.form.ui.components.DatePickerBirthDate
import com.almy.curp.form.ui.components.DropdownStates
import com.almy.curp.form.ui.components.RadioButtonGroupSex
import com.almy.curp.ui.theme.CURPTheme


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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Form(viewModel: FormViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    val focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()

    Scaffold( scaffoldState = scaffoldState,
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                CustomInput(
                    label = "Nombres",
                    value = data.name,
                    onChangeValue = {viewModel.onChangeName(it)  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )
                CustomInput(
                    label = "Primer Apellido",
                    value = data.middleName,
                    onChangeValue = {viewModel.onChangeMiddleName(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )
                CustomInput(
                    label = "Segundo Apellido",
                    value =  data.lastName,
                    onChangeValue = { viewModel.onChangeLastName(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )
                DatePickerBirthDate(
                    label = "Fecha Nacimiento",
                    value = data.birth,
                    onValueChange = {viewModel.onChangeBirth(it) },
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
                    onItemClick = { viewModel.onChangeGender(it)  }

                )
                DropdownStates(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    selected = data.state,
                    label = "Estado",
                    listItems = data.statesList,
                    onValueChange = {viewModel.onChangeState(it) }
                )

            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Generador de CURP", color = Color.White) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.generarCURP() }) {
                Icon(Icons.Filled.Done, contentDescription = "Generar CURP")
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, device = Devices.NEXUS_10)
@Composable
fun DefaultPreview() {
    val viewModel = FormViewModel()
    CURPTheme{
        Form(viewModel)
    }
}
