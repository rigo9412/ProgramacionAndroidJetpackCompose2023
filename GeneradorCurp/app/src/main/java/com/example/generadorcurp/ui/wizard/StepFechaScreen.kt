package com.example.generadorcurp.ui.wizard

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
import com.example.generadorcurp.GlobalProvider
import com.example.generadorcurp.R
import com.example.generadorcurp.components.DatePick
import com.example.generadorcurp.ui.nav.Screens
import com.example.generadorcurp.ui.wizard.components.StepLayout

@Composable
fun StepFechaScreen(onEvent: (WizardScreenEvent) -> Unit){
        val focusManager = LocalFocusManager.current

        val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value
        StepLayout(

            title = "Fecha de Nacimiento",
            subtitle = "Agrega tu fecha de nacimiento",
            onBack = {
                onEvent(WizardScreenEvent.Back(Screens.StepFechaScreen.route,Screens.StepNombreScreen.route))
            },
            onSubmit = {
                onEvent(WizardScreenEvent.StepFechaSubmit)
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    DatePick(
                        label = stringResource(R.string.fecha),
                        value = data.fechaNacimiento,
                        onValueChange = {
                            onEvent(WizardScreenEvent.FechaChanged(it))
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