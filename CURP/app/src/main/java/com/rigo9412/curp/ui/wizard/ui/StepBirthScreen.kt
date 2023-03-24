package com.rigo9412.curp.ui.wizard.ui

import DatePickerBirthDate
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
import com.rigo9412.curp.ui.form.ui.CurpFormEvent
import com.rigo9412.curp.ui.nav.Screens
import com.rigo9412.curp.ui.form.ui.CurpFormModelState
import com.rigo9412.curp.ui.wizard.ui.components.StepLayout

@Composable
fun StepBitrhScreen(
    data: CurpFormModelState,
    onEvent: (WizardScreenEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    StepLayout(

        title = "Fecha de Nacimiento",
        subtitle = "Agrega tu fecha de nacimiento",
        onBack = {
            onEvent(WizardScreenEvent.Back(Screens.StepBirthScreen.route,Screens.StepNameScreen.route))
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepNameSubmit)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                DatePickerBirthDate(
                    label = stringResource(R.string.birth),
                    value = data.birth,
                    onValueChange = {
                        onEvent(WizardScreenEvent.BirthChanged(it))
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