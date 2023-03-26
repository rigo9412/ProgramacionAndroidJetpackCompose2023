package com.game.curp.ui.wizard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.curp.GlobalProvider
import com.game.curp.ui.nav.Screens
import com.game.curp.ui.wizard.components.StepLayout

@RequiresApi(Build.VERSION_CODES.O)
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
                Text(text = "Hola", fontSize = 52.sp, fontWeight = FontWeight.Bold)
                Text(text = "Completa los pasos para generar tu CURP", fontSize = 32.sp)
            }
        }
    )
}