package com.morin.curp.curp.ui.wizard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.morin.curp.GlobalProvider
import com.morin.curp.R
import com.morin.curp.curp.components.DropdownStates
import com.morin.curp.curp.ui.nav.Screens
import com.morin.curp.curp.ui.wizard.ui.components.StepLayout

@Composable
fun StepStateScreen(onEvent: (WizardScreenEvent) -> Unit) {
    val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value
    StepLayout(
        isLast = true,
        title = "Estado",
        subtitle = "Registra el estado donde naciste",
        onBack = {
            onEvent(
                WizardScreenEvent.Back(
                    Screens.StepStateScreen.route,
                    Screens.StepGenderScreen.route
                )
            )
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepStateSubmit)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                DropdownStates(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    selected = data.state,
                    label = stringResource(R.string.state),
                    listItems = data.statesList,
                    onValueChange = {
                        onEvent(WizardScreenEvent.StateChanged(it))
                    }
                )
            }
        }
    )
}