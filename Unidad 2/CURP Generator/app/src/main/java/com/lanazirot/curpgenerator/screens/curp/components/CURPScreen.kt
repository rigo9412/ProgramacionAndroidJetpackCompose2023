package com.lanazirot.curpgenerator.screens.curp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lanazirot.curpgenerator.R
import com.lanazirot.curpgenerator.domain.enums.Gender
import com.lanazirot.curpgenerator.screens.curp.viewmodel.PersonViewModel
import com.lanazirot.curpgenerator.ui.theme.CURPGeneratorTheme

@Composable
fun CURPScreen(personViewModel: PersonViewModel = PersonViewModel()) {

    //Focus manager
    val focusManager = LocalFocusManager.current
    val state by personViewModel.personState.collectAsState()
    var curpDialogOpened by remember { mutableStateOf(false) }

    CURPGeneratorTheme(darkTheme = false) {
        if (curpDialogOpened) {
            CustomAlertDialog(
                message = "CURP: ${personViewModel.generateCURP()}",
                showDialog = curpDialogOpened,
                onDismiss = { curpDialogOpened = false }
            )
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                CustomInput(
                    value = state.person.name,
                    label = stringResource(id = R.string.input_name_user),
                    focusManager = focusManager,
                    capitalization = KeyboardCapitalization.Characters,
                    onValueChange = {
                        personViewModel.updatePerson(
                            state.person.copy(name = it)
                        )
                    })

                CustomInput(
                    value = state.person.lastname,
                    label = stringResource(id = R.string.input_lastname_user),
                    focusManager = focusManager,
                    capitalization = KeyboardCapitalization.Characters,
                    onValueChange = {
                        personViewModel.updatePerson(
                            state.person.copy(lastname = it)
                        )
                    })

                CustomInput(
                    value = state.person.surname,
                    label = stringResource(id = R.string.input_surname_user),
                    focusManager = focusManager,
                    lastInput = true,
                    capitalization = KeyboardCapitalization.Characters,
                    onValueChange = {
                        personViewModel.updatePerson(
                            state.person.copy(surname = it)
                        )
                    })

                CustomInputRadioButtonGroup(
                    options = listOf(
                        Gender.MALE,
                        Gender.FEMALE,
                        Gender.NON_BINARY
                    ),
                    selectedOption = state.person.gender,
                    onOptionSelected = {
                        personViewModel.updatePerson(
                            state.person.copy(gender = it)
                        )
                    })

                CustomInputDate(
                    value = state.person.birthDate,
                    label = stringResource(id = R.string.input_birthdate_user),
                    onValueChange = {
                        personViewModel.updatePerson(
                            state.person.copy(birthDate = it)
                        )
                    })

                Spacer(modifier = Modifier.padding(6.dp))

                CustomInputDropdownStates(
                    value = state.person.state,
                    onValueChange = { a ->
                        personViewModel.updatePerson(
                            state.person.copy(state = a)
                        )
                    })

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        curpDialogOpened = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.btn_generar_curp))
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CURPGeneratorTheme {
        CURPScreen()
    }
}