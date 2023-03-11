package com.rigo9412.curp.form.ui

import DatePickerBirthDate
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rigo9412.curp.ui.theme.CURPTheme


@Composable
fun FormScreen(viewModel: FormViewModel) {
    val state = viewModel.uiState.collectAsState().value
    when (state) {
        FormUiState.Empty -> Text(text = "vicio")
        is FormUiState.Error -> Text(text = "error")
        is FormUiState.Loaded -> Form(viewModel)
        FormUiState.Loading -> Text(text = "cargando")
    }
}

@Composable
fun Form(viewModel: FormViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        CustomInput(
            label = stringResource(com.example.curp.R.string.name),
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

        Button(
            enabled = data.isValid,
            onClick = { viewModel.generateCURP() }) {
            
            Text(text = "GENERAR CURP")
        }
        
        if(data.curp != ""){
            Text(text = data.curp)
        }

    }
}

@Preview(showBackground = true, device = Devices.NEXUS_10)
@Composable
fun DefaultPreview() {
    val viewModel = FormViewModel()
    CURPTheme {
        FormScreen(viewModel)
    }
}

