package com.lanazirot.curpavanzado.screens.components.wizard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lanazirot.curpavanzado.R
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.domain.events.WizardScreenEvent
import com.lanazirot.curpavanzado.provider.LocalGlobalProvider
import com.lanazirot.curpavanzado.screens.components.common.CustomInputDate
import com.lanazirot.curpavanzado.screens.components.step.StepScreen


@Composable
fun BirthdateScreen() {

    val gp = LocalGlobalProvider.current
    val personViewModel = gp.wizardVM
    val state by personViewModel.personState.collectAsState()

    StepScreen(
        title = stringResource(id = R.string.screen_birth_date_title),
        subtitle = stringResource(id = R.string.screen_birth_date_subtitle),
        isLast = false,
        onNext = {
            personViewModel.onEvent(WizardScreenEvent.StepBirthSubmit)
        },
        onBack = {
            personViewModel.onEvent(WizardScreenEvent.Back(origin = Routes.WizardBirthDate.route, destination = Routes.WizardName.route))
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CustomInputDate(
                value = state.person.birthDate,
                label = stringResource(id = R.string.screen_birth_date_subtitle),
                onValueChange = {
                    personViewModel.updatePerson(
                        state.person.copy(birthDate = it)
                    )
                })
        }
    }
}

