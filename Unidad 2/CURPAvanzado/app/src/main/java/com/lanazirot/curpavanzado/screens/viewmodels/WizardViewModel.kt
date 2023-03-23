package com.lanazirot.curpavanzado.screens.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lanazirot.curpavanzado.screens.states.WizardScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WizardViewModel @Inject constructor() : ViewModel() {

    private var _wizardScreenState = MutableStateFlow<WizardScreenState>(WizardScreenState.Welcome())
    val wizardScreenState = _wizardScreenState.asStateFlow()

    init{
        _wizardScreenState.value = WizardScreenState.Welcome()
    }

    fun onSubmit(currentScreen: WizardScreenState){
        when(currentScreen){
            is WizardScreenState.Welcome -> _wizardScreenState.value = WizardScreenState.WizardNameScreen()
            is WizardScreenState.WizardNameScreen -> _wizardScreenState.value = WizardScreenState.WizardBirthDateScreen()
            is WizardScreenState.WizardBirthDateScreen -> _wizardScreenState.value = WizardScreenState.WizardGenderScreen()
            is WizardScreenState.WizardGenderScreen -> _wizardScreenState.value = WizardScreenState.WizardStateScreen()
            is WizardScreenState.WizardStateScreen -> _wizardScreenState.value = WizardScreenState.ResultScreen()
            is WizardScreenState.ResultScreen -> _wizardScreenState.value = WizardScreenState.Welcome()
        }
    }

}