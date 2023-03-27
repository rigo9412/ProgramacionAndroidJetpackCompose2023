package com.example.generadorcurp.ui.wizard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.generadorcurp.GlobalProvider
import com.example.generadorcurp.components.RadioButtonPairGroup
import com.example.generadorcurp.ui.nav.Screens
import com.example.generadorcurp.ui.wizard.components.StepLayout

@Composable
fun StepGeneroScreen(onEvent: (WizardScreenEvent) -> Unit){
    val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value
    StepLayout(
        title = "Genero",
        subtitle = "Agrega el genero con el que estas registrado",
        onBack = {
            onEvent(WizardScreenEvent.Back(Screens.StepGeneroScreen.route,Screens.StepFechaScreen.route))
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepGeneroSubmit)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                RadioButtonPairGroup(
                    label = "Genero",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    items = data.generoList,
                    selection = data.genero.first,
                    onItemClick = {
                        onEvent(WizardScreenEvent.GeneroChanged(it))
                    }

                )
            }
        }
    )
}