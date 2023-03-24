package com.rigo9412.curp.ui.wizard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.curp.R
import com.rigo9412.curp.form.ui.CustomInput
import com.rigo9412.curp.ui.nav.Screens
import com.rigo9412.curp.ui.form.ui.CurpFormModelState
import com.rigo9412.curp.ui.wizard.ui.components.StepLayout

@Composable
fun StepNameScreen(
    data: CurpFormModelState,
    onEvent: (WizardScreenEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    StepLayout(
        isFirst = true,
        title = stringResource(R.string.step_name),
        subtitle = stringResource(R.string.step_name_subtitle),
        onBack = {

            onEvent(WizardScreenEvent.Back(Screens.StepNameScreen.route,Screens.HomeScreen.route))
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepNameSubmit)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                CustomInput(
                    label = stringResource(R.string.name),
                    value = data.name,
                    error = data.nameError,
                    onChangeValue = {
                        onEvent(WizardScreenEvent.NameChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )
                CustomInput(
                    label = "Primer Apellido",
                    value = data.middleName,
                    error = data.middleNameError,
                    onChangeValue = {
                        onEvent(WizardScreenEvent.MiddleNameChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )
                CustomInput(
                    label = "Segundo Apellido",
                    value = data.lastName,
                    error = data.lastNameError,
                    onChangeValue = {
                        onEvent(WizardScreenEvent.LastNameChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    focusManager = focusManager
                )
            }
        }
    )
}