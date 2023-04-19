package com.lanazirot.curpavanzado.screens.viewmodels

import androidx.lifecycle.ViewModel
import com.lanazirot.curpavanzado.cases.BirthdateValidation
import com.lanazirot.curpavanzado.cases.NameValidation
import com.lanazirot.curpavanzado.cases.ValidationResult
import com.lanazirot.curpavanzado.domain.events.WizardScreenEvent
import com.lanazirot.curpavanzado.screens.states.PersonState
import com.lanazirot.curpavanzado.screens.states.WizardScreenState
import com.lanazirot.curpavanzado.services.interfaces.ICURPGenerator
import com.lanazirot.curpavanzado.domain.models.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WizardViewModel @Inject constructor(
    private val curpGenerator: ICURPGenerator
) : ViewModel() {

    private val nameValidator: NameValidation = NameValidation()
    private val birthValidator: BirthdateValidation = BirthdateValidation()

    private var _wizardScreenState = MutableStateFlow<WizardScreenState>(WizardScreenState.Welcome)
    val wizardScreenState = _wizardScreenState.asStateFlow()

    private var _personState = MutableStateFlow(PersonState())
    var personState: StateFlow<PersonState> = _personState.asStateFlow()

    fun updatePerson(updatedPerson: Person) {
        _personState.value = _personState.value.copy(person = updatedPerson)
    }

    fun resetPerson() {
        _personState.value = PersonState()
    }

    init {
        _wizardScreenState.value = WizardScreenState.Welcome
    }

    fun onEvent(wizardEvent: WizardScreenEvent) {
        when (wizardEvent) {
            is WizardScreenEvent.Back -> back(wizardEvent.origin, wizardEvent.destination)
            is WizardScreenEvent.StepNameSubmit -> validateStepName()
            is WizardScreenEvent.StepBirthSubmit -> validateStepBirthDate()
            is WizardScreenEvent.StepStateSubmit -> generateCurp()
            is WizardScreenEvent.StepGenderSubmit  -> _wizardScreenState.value = WizardScreenState.WizardStateScreen
            is WizardScreenEvent.StepDone -> validateData()
        }
    }

    private fun generateCurp() {
        val curp = curpGenerator.generate(personState.value.person)
        _wizardScreenState.value = WizardScreenState.ResultScreen(curp, personState.value.person.name, personState.value.person.lastname)
    }
    private fun validateStepName() {
        val name = personState.value.person.name
        val lastName = personState.value.person.lastname

        val validationResult = validateName(name, lastName)

        _wizardScreenState.value = when (validationResult) {
            is ValidationResult.Invalid -> WizardScreenState.Error(validationResult.message)
            is ValidationResult.Valid -> WizardScreenState.WizardBirthDateScreen
        }
    }

    private fun validateStepBirthDate() {
        val birthdate = personState.value.person.birthDate
        val validBirthDate = birthValidator.validateBirthdate(birthdate)

        _wizardScreenState.value = when (validBirthDate) {
            is ValidationResult.Invalid -> WizardScreenState.Error(validBirthDate.message)
            is ValidationResult.Valid -> WizardScreenState.WizardGenderScreen
        }
    }

    private fun validateData() {
        val person = personState.value.person

        val validationResult = validateName(person.name, person.lastname)

        _wizardScreenState.value = when (validationResult) {
            is ValidationResult.Invalid -> WizardScreenState.Error(validationResult.message)
            is ValidationResult.Valid -> {
                when (val validBirthDate = birthValidator.validateBirthdate(person.birthDate)) {
                    is ValidationResult.Invalid -> WizardScreenState.Error(validBirthDate.message)
                    is ValidationResult.Valid -> WizardScreenState.ResultScreen(curpGenerator.generate(person), person.name, person.lastname)
                }
            }
        }
    }

    private fun validateName(name: String, lastName: String): ValidationResult {
        val validName = nameValidator.validateName(name)
        val validLastName = nameValidator.validateLastName(lastName)

        return when {
            validName is ValidationResult.Invalid -> validName
            validLastName is ValidationResult.Invalid -> validLastName
            else -> ValidationResult.Valid
        }
    }

    private fun back(origin: String, destination: String) {
        _wizardScreenState.value = WizardScreenState.WizardBack(origin, destination)
    }

}