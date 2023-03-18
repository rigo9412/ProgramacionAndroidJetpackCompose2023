package com.ezequiel.curp.form.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ezequiel.curp.form.components.EmptyView
import com.ezequiel.curp.form.components.ErrorView
import com.ezequiel.curp.form.components.LoadingView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen(viewModel: FormViewModel,navigationController: NavHostController) {
    val state = viewModel.uiState.collectAsState().value

    when (state) {
        is FormViewModel.FormUiState.Error -> ErrorView(error = state.message) { viewModel.initState() }
        is FormViewModel.FormUiState.Loaded -> ForScreen(viewModel)
        is FormViewModel.FormUiState.Loading -> LoadingView(message = state.message)
        is FormViewModel.FormUiState.Done -> EmptyView(action = {
            navigationController.navigate("result/${state.curp}/${state.name}}")
        })
        else -> {

        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForScreen(viewModel: FormViewModel){

    var data = viewModel.uiStateData.collectAsState().value
    var focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                CustomInput(
                    label = stringResource(com.ezequiel.curp.R.string.name),
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
                    value = data.lastName,
                    onChangeValue = { viewModel.onChangeLastname(it) },
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
                DatePickerBirthDate(
                    label = "Fecha Nacimiento",
                    value = data.birth,
                    onValueChange = { viewModel.onChangeBirth(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager

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
                title = { Text("CURP", color = Color.White) },
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


@Composable
fun CustomInput(
    label: String,
    value:String,
    onChangeValue: (String) -> Unit,
    modifier: Modifier,
    focusManager: FocusManager
){
    TextField(
        value = value,
        onValueChange = onChangeValue,
        modifier = modifier,
        label = { Text(text = label) },
        isError = false,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        )
    )
}
