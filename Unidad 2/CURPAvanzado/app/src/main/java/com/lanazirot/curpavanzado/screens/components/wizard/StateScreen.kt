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
import com.lanazirot.curpavanzado.screens.components.common.CustomInputDropdownStates
import com.lanazirot.curpavanzado.screens.components.step.StepScreen


@Composable
fun StateScreen(
) {

    val gp = LocalGlobalProvider.current
    val personViewModel = gp.wizardVM

    val state by personViewModel.personState.collectAsState()

    StepScreen(
        title = stringResource(id = R.string.screen_birth_state_title),
        subtitle = stringResource(id = R.string.screen_birth_state_subtitle),
        isLast = true,
        onNext = {
            personViewModel.onEvent(WizardScreenEvent.StepStateSubmit)
        },
        onBack = {
            personViewModel.onEvent(WizardScreenEvent.Back(Routes.WizardState.route, Routes.WizardGender.route))
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CustomInputDropdownStates(
                value = state.person.state,
                label = stringResource(id = R.string.screen_birth_state_subtitle),
                onValueChange = { a ->
                    personViewModel.updatePerson(
                        state.person.copy(state = a)
                    )
                })
        }
    }
}

