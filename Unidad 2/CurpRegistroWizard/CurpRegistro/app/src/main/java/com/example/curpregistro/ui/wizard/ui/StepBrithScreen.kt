package com.example.curpregistro.ui.wizard.ui


import android.os.Build
import androidx.annotation.RequiresApi
import com.example.curpregistro.components.DatePickerBirthDate
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
import com.example.curpregistro.GlobalProvider
import com.example.curpregistro.R
import com.example.curpregistro.ui.nav.Screens
import com.example.curpregistro.ui.wizard.ui.components.StepLayout


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StepBitrhScreen(
    onEvent: (WizardScreenEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current

    val data = GlobalProvider.current.wizardVM.uiStateData.collectAsState().value
    StepLayout(

        title = "Fecha de Nacimiento",
        subtitle = "Agrega tu fecha de nacimiento",
        onBack = {
            onEvent(WizardScreenEvent.Back(Screens.StepBirthScreen.route,Screens.StepNameScreen.route))
        },
        onSubmit = {
            onEvent(WizardScreenEvent.StepBirthSubmit)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                DatePickerBirthDate(
                    label = stringResource(R.string.birth),
                    value = data.birth,
                    onValueChange = {
                        onEvent(WizardScreenEvent.BirthChanged(it))
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