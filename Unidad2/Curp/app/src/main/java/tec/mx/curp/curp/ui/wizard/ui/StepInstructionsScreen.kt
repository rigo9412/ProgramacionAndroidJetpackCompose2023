package tec.mx.curp.curp.ui.wizard.ui

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
import tec.mx.curp.GlobalProvider
import tec.mx.curp.curp.domain.nav.Screens
import tec.mx.curp.curp.ui.wizard.ui.components.StepLayout

@Composable
@Preview
fun StepInstructionsScreen() {
    val wizardVM = GlobalProvider.current.wizardVM

    StepLayout(
        isFirst = true,
        title = "CURP Paso a Paso",
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
                Text(text = "Te guiaremos de forma sencilla y paso a paso para completar tu CURP", fontSize = 32.sp)
            }
        }
    )
}