package com.myriam.curp2.ui.wizard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.myriam.curp2.form.ui.RadioButtonGroupSex
import com.myriam.curp2.GlobalProvider


import com.myriam.curp2.ui.nav.Screens
import com.myriam.curp2.ui.wizard.ui.components.StepLayout

@Composable
fun StepGenderScreen(
    onEvent: (WizardScreenEvent) -> Unit
) {
    val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value
    StepLayout(
        title = "Genero",
        subtitle = "Agrega el genero con el que estas registrado",
        onBack = {
            onEvent(WizardScreenEvent.Back(Screens.StepGenderScreen.route,Screens.StepBirthScreen.route))
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepGenderSubmit)
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