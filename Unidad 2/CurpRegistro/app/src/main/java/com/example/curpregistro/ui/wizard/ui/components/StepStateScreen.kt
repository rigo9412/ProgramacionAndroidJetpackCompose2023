package com.example.curpregistro.ui.wizard.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.curpregistro.GlobalProvider
import com.example.curpregistro.R
import com.example.curpregistro.components.DropdownStates
import com.example.curpregistro.ui.nav.Screens
import com.example.curpregistro.ui.wizard.ui.WizardScreenEvent


@Composable
fun StepStateScreen(onEvent: (WizardScreenEvent) -> Unit) {

    val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value
    StepLayout(
        isLast = true,
        title = "Estado",
        subtitle = "Agrega el estado en el naciste",
        onBack = {

            onEvent(WizardScreenEvent.Back(Screens.StepStateScreen.route,Screens.StepGenderScreen.route))
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