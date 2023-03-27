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
import com.example.generadorcurp.form.domain.ui.components.PairListDropdown
import com.example.generadorcurp.ui.nav.Screens
import com.example.generadorcurp.ui.wizard.components.StepLayout

@Composable
fun StepEstadoScreen(onEvent: (WizardScreenEvent) -> Unit){
    val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value
    StepLayout(
        isLast = true,
        title = "Estado",
        subtitle = "Agrega el estado en el naciste",
        onBack = {

            onEvent(WizardScreenEvent.Back(Screens.StepEstadoScreen.route,Screens.StepGeneroScreen.route))
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepEstadoSubmit)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                PairListDropdown(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    selected = data.estado,
                    label = stringResource(R.string.estado),
                    listItems = data.estadoList,
                    onValueChange = {
                        onEvent(WizardScreenEvent.EstadoChanged(it))
                    }

                )
            }
        }
    )
}