package com.morin.curp.curp.ui.wizard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.morin.curp.GlobalProvider
import com.morin.curp.R
import com.morin.curp.curp.components.CustomInput
import com.morin.curp.curp.ui.nav.Screens
import com.morin.curp.curp.ui.wizard.ui.components.StepLayout

@Composable
fun StepNameScreen(onEvent: (WizardScreenEvent) -> Unit) {
    val focusManager = LocalFocusManager.current
    val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value
    StepLayout(
        title = stringResource(R.string.step_name),
        subtitle = stringResource(R.string.step_name_subtitle),
        onBack = {
            onEvent(WizardScreenEvent.Back(Screens.StepNameScreen.route, Screens.HomeScreen.route))
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