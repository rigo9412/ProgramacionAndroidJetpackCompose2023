package com.example.registrocurp.form.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.registrocurp.form.domain.estados
import com.example.registrocurp.form.domain.sexos

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForScreen(){
    var name by remember { mutableStateOf("") }
    var name1 by remember { mutableStateOf("") }
    var name2 by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var fechaN by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState()))
    {
        CustomInput(
            label = stringResource(com.example.registrocurp.R.string.name),
            value = name,
            onChangeValue = { name = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
        CustomInput(
            label = "Apellido Paterno",
            value = name1,
            onChangeValue = { name1 = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
        CustomInput(
            label = "Apellido Materno",
            value = name2,
            onChangeValue = { name2 = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
        RadioButtonGroupSex(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            items = sexos,
            selection = sexo,
            onItemClick = {sexo = it}
        )
        DatePickerDate(
            label = "Fecha de nacimiento",
            value = fechaN,
            onValueChange = { fechaN = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
        DropdownStates(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            selected = estados.first { true },
            label = "Estado",
            listItems = estados,
            onValueChange = { estado = it.toString() }

        )
    }
}