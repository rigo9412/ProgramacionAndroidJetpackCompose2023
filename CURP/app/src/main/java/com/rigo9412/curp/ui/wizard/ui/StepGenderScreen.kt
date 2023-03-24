package com.rigo9412.curp.ui.wizard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.rigo9412.curp.form.ui.RadioButtonGroupSex
import com.rigo9412.curp.ui.form.ui.CurpFormEvent
import com.rigo9412.curp.ui.form.ui.CurpFormModelState
import com.rigo9412.curp.ui.nav.Screens
import com.rigo9412.curp.ui.wizard.ui.components.StepLayout

@Composable
fun StepGenderScreen(
    data: CurpFormModelState,
    onEvent: (WizardScreenEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    StepLayout(
        title = "Genero",
        subtitle = "Agrega el genero con el que estas registrado",
        onBack = {
            onEvent(WizardScreenEvent.Back(Screens.StepGenderScreen.route,Screens.StepBirthScreen.route))
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepNameSubmit)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                RadioButtonGroupSex(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    items = data.sexList,
                    selection = data.gender.first,
                    onItemClick = {
                        onEvent(WizardScreenEvent.GenderChanged(it))
                    }

                )
            }
        }
    )
}