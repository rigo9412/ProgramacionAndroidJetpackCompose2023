package com.example.formulario.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource


@Composable
fun StepNameScreen(
    name:String,
    middleName: String,
    lastName: String,
//    onEvent: (wizardScreenEvent) -> Unit
){
    val focusManager = LocalFocusManager.current
//    StepLayout(
//        isFirst = true,
//        title = "Nombre completo",
//        subtitle = "Completa los cuadros que se indican",
//        onBack={
//          onEvent(WizardScreenEvent().close)
//        },
//        onSubmit={
//            onEvent(WizardScreenEvent().StepNameSubmit)
//        },
//        content= {
//            Column(modifier = Modifier.fillMaxSize()){
//                CustomInput(label = stringResource("Nombre(s)"), value = , onChangeValue = , modifier = , focusManager = )
//            }
//        }
//    )

}