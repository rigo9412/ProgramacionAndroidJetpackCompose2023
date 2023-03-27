package com.example.generadorcurp.ui.wizard

import android.provider.Settings.Global
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.generadorcurp.GlobalProvider
import com.example.generadorcurp.R
import com.example.generadorcurp.components.CustomTextField
import com.example.generadorcurp.ui.nav.Screens
import com.example.generadorcurp.ui.wizard.components.StepLayout


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StepNameScreen(onEvent: (WizardScreenEvent) -> Unit) {
    val focusManager = LocalFocusManager.current
    val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value

    StepLayout(

        title = stringResource(R.string.nombre),
        subtitle = stringResource(R.string.nombre),
        onBack = {

            onEvent(WizardScreenEvent.Back(Screens.StepNombreScreen.route,Screens.HomeScreen.route))
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepNombreSubmit)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                CustomTextField(
                    label = stringResource(R.string.nombre),
                    value = data.nombre,
                    error = data.nombreError,
                    onChangeValue = {
                        onEvent(WizardScreenEvent.NombreChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
                CustomTextField(
                    label = "Primer Apellido",
                    value = data.paterno,
                    error = data.paternoError,
                    onChangeValue = {
                        onEvent(WizardScreenEvent.PaternoChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
                CustomTextField(
                    label = "Segundo Apellido",
                    value = data.materno,
                    error = data.maternoError,
                    onChangeValue = {
                        onEvent(WizardScreenEvent.MaternoChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    imeAction= ImeAction.Done,
                    onAction = {
                        focusManager.clearFocus()
                    }
                )
            }
        }
    )
}