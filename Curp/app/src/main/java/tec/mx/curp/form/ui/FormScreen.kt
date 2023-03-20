package tec.mx.curp.form.ui

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import tec.mx.curp.R
import tec.mx.curp.components.EmptyView
import tec.mx.curp.components.ErrorView
import tec.mx.curp.components.LoadingView
import tec.mx.curp.form.ui.components.CustomInput
import tec.mx.curp.form.ui.components.DatePickerDate
import tec.mx.curp.form.ui.components.DropdownStates
import tec.mx.curp.form.ui.components.RadioButtonGroupSex

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
                Text(text = "Ingresa tu nombre:", modifier = Modifier.padding(20.dp,10.dp,0.dp,10.dp))
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
                Text(text = "Selecciona tu fecha de Nacimiento:", modifier = Modifier.padding(20.dp,10.dp,0.dp,10.dp))
                DatePickerDate(
                    label = "Fecha Nacimiento",
                    value = data.birth,
                    onValueChange = { viewModel.onChangeBirth(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )
                Text(text = "Selecciona tu sexo:", modifier = Modifier.padding(20.dp,10.dp,0.dp,10.dp))
                RadioButtonGroupSex(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    items = data.sexList,
                    selection = data.gender.first,
                    onItemClick = { viewModel.onChangeGender(it) }

                )
                Text(text = "Selecciona tu estado de Nacimiento:", modifier = Modifier.padding(20.dp,10.dp,0.dp,10.dp))
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
                title = { Text("Consultor de CURP 📃", color = Color.White) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.generateCURP()
            }) {
                Icon(Icons.Filled.Done, contentDescription = "Generar CURP")
            }
        }
    )
}