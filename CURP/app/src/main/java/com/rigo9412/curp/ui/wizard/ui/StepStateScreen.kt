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
import com.rigo9412.curp.form.ui.DropdownStates
import com.rigo9412.curp.ui.form.ui.CurpFormEvent
import com.rigo9412.curp.ui.form.ui.CurpFormModelState
import com.rigo9412.curp.ui.nav.Screens
import com.rigo9412.curp.ui.wizard.ui.components.StepLayout

@Composable
fun StepStateScreen(
    data: CurpFormModelState,
    onEvent: (WizardScreenEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    StepLayout(
        isLast = true,
        title = "Estado",
        subtitle = "Agrega el estado en el naciste",
        onBack = {

            onEvent(WizardScreenEvent.Back(Screens.StepStateScreen.route,Screens.StepGenderScreen.route))
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepNameSubmit)
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