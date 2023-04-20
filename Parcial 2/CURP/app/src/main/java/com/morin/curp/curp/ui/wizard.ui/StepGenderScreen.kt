package com.morin.curp.curp.ui.wizard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.morin.curp.GlobalProvider
import com.morin.curp.curp.components.RadioButtonGroupSex
import com.morin.curp.curp.ui.nav.Screens
import com.morin.curp.curp.ui.wizard.ui.components.StepLayout

@Composable
fun StepGenderScreen(
    onEvent: (WizardScreenEvent) -> Unit
) {
    val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value
    StepLayout(
        title = "Genero",
        subtitle = "Agrega el genero:",
        onBack = {
            onEvent(
                WizardScreenEvent.Back(
                    Screens.StepGenderScreen.route,
                    Screens.StepBirthScreen.route
                )
            )
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