package com.lanazirot.curpavanzado.screens.components.manual

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.lanazirot.curpavanzado.R
import com.lanazirot.curpavanzado.domain.enums.Gender
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.domain.events.WizardScreenEvent
import com.lanazirot.curpavanzado.provider.LocalGlobalProvider
import com.lanazirot.curpavanzado.screens.components.common.CustomInput
import com.lanazirot.curpavanzado.screens.components.common.CustomInputDate
import com.lanazirot.curpavanzado.screens.components.common.CustomInputDropdownStates
import com.lanazirot.curpavanzado.screens.components.common.CustomInputRadioButtonGroup
import com.lanazirot.curpavanzado.screens.components.step.StepScreen


@Composable
fun ManualScreen() {
    val gp = LocalGlobalProvider.current
    val personViewModel = gp.wizardVM

    val focusManager = LocalFocusManager.current
    val state by personViewModel.personState.collectAsState()

    StepScreen(
        title = stringResource(id = R.string.screen_personal_data_title),
        subtitle = stringResource(id = R.string.screen_manual_data),
        isLast = true,
        onNext = {
            personViewModel.onEvent(WizardScreenEvent.StepDone)
        },
        onBack = {
            personViewModel.onEvent(
                WizardScreenEvent.Back(
                    origin = Routes.Manual.route,
                    destination = Routes.Welcome.route
                )
            )
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
                modifier = Modifier.testTag("manual_mode_name"),
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
                modifier = Modifier.testTag("manual_mode_lastname"),
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
                modifier = Modifier.testTag("manual_mode_surname"),
                onValueChange = {
                    personViewModel.updatePerson(
                        state.person.copy(surname = it)
                    )
                })

            CustomInputRadioButtonGroup(
                options = listOf(
                    Gender.MALE,
                    Gender.FEMALE,
                    Gender.NON_BINARY
                ),
                selectedOption = state.person.gender,
                modifier = Modifier.testTag("manual_mode_gender"),
                onOptionSelected = {
                    personViewModel.updatePerson(
                        state.person.copy(gender = it)
                    )
                })

            CustomInputDate(
                value = state.person.birthDate,
                label = stringResource(id = R.string.screen_birth_date_subtitle),
                modifier = Modifier.testTag("manual_mode_birthdate"),
                onValueChange = {
                    personViewModel.updatePerson(
                        state.person.copy(birthDate = it)
                    )
                })

            Spacer(modifier = Modifier.padding(6.dp))

            CustomInputDropdownStates(
                label = stringResource(id = R.string.screen_birth_state_subtitle),
                modifier = Modifier.testTag("manual_mode_surname"),
                value = state.person.state,
                onValueChange = { a ->
                    personViewModel.updatePerson(
                        state.person.copy(state = a)
                    )
                })
        }
    }
}

