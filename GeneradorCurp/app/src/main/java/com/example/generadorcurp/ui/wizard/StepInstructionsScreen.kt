package com.example.generadorcurp.ui.wizard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.generadorcurp.GlobalProvider
import com.example.generadorcurp.ui.nav.Screens
import com.example.generadorcurp.ui.wizard.components.StepLayout

@Composable
@Preview
fun StepInstructionsScreen() {
    val wizardVM = GlobalProvider.current.wizardVM

    StepLayout(
        isFirst = true,
        title = "Wizard CURP ",
        subtitle = "",
        onBack = {
            wizardVM.onEvent(WizardScreenEvent.Back(Screens.StepInstructionsScreen.route, Screens.HomeScreen.route))
        },
        onSubmit = {
            wizardVM.onEvent(WizardScreenEvent.Start)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Bienvenido", fontSize = 52.sp, fontWeight = FontWeight.Bold)
                Text(text = "Te guiaremos para completar tu CURP", fontSize = 32.sp)
            }
        }
    )
}