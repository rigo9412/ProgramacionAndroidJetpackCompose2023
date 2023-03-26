package com.lanazirot.curpavanzado.screens.components.wizard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.lanazirot.curpavanzado.R
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.domain.events.WizardScreenEvent
import com.lanazirot.curpavanzado.provider.LocalGlobalProvider
import com.lanazirot.curpavanzado.screens.components.common.CustomInput
import com.lanazirot.curpavanzado.screens.components.step.StepScreen



@Composable
fun NameScreen() {

    val gp = LocalGlobalProvider.current
    val personViewModel = gp.wizardVM

    val focusManager = LocalFocusManager.current
    val state by personViewModel.personState.collectAsState()

    StepScreen(
        title = stringResource(id = R.string.screen_personal_data_title),
        subtitle = stringResource(id = R.string.screen_personal_data_subtitle),
        isLast = false,
        onNext = {
            personViewModel.onEvent(WizardScreenEvent.StepNameSubmit)
        },
        onBack = {
            personViewModel.onEvent(WizardScreenEvent.Back(origin = Routes.WizardName.route, destination = Routes.Welcome.route))
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CustomInput(
                value = state.person.name,
                label = stringResource(id = R.string.screen_personal_data_name),
                focusManager = focusManager,
                capitalization = KeyboardCapitalization.Characters,
                onValueChange = {
                    personViewModel.updatePerson(
                        state.person.copy(name = it)
                    )
                })

            CustomInput(
                value = state.person.lastname,
                label = stringResource(id = R.string.screen_personal_data_surname),
                focusManager = focusManager,
                capitalization = KeyboardCapitalization.Characters,
                onValueChange = {
                    personViewModel.updatePerson(
                        state.person.copy(lastname = it)
                    )
                })

            CustomInput(
                value = state.person.surname,
                label = stringResource(id = R.string.screen_personal_data_mother_surname),
                focusManager = focusManager,
                lastInput = true,
                capitalization = KeyboardCapitalization.Characters,
                onValueChange = {
                    personViewModel.updatePerson(
                        state.person.copy(surname = it)
                    )
                })
        }
    }
}

