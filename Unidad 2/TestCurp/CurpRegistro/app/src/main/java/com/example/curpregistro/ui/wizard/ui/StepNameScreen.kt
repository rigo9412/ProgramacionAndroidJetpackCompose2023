package com.example.curpregistro.ui.wizard.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.curpregistro.R
import com.example.curpregistro.GlobalProvider
import com.example.curpregistro.components.CustomInput
import com.example.curpregistro.ui.nav.Screens
import com.example.curpregistro.ui.wizard.ui.components.StepLayout


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StepNameScreen(onEvent: (WizardScreenEvent) -> Unit) {
    val focusManager = LocalFocusManager.current
    val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value

    StepLayout(

        title = stringResource(R.string.step_name),
        subtitle = stringResource(R.string.step_name_subtitle),
        onBack = {

            onEvent(WizardScreenEvent.Back(Screens.StepNameScreen.route,Screens.HomeScreen.route))
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepNameSubmit)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                CustomInput(
                    label = stringResource(R.string.name),
                    value = data.name,
                    error = data.nameError,
                    onChangeValue = {
                        onEvent(WizardScreenEvent.NameChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
                CustomInput(
                    label = "Primer Apellido",
                    value = data.middleName,
                    error = data.middleNameError,
                    onChangeValue = {
                        onEvent(WizardScreenEvent.MiddleNameChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onAction = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
                CustomInput(
                    label = "Segundo Apellido",
                    value = data.lastName,
                    error = data.lastNameError,
                    onChangeValue = {
                        onEvent(WizardScreenEvent.LastNameChanged(it))
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