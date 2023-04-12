package tec.mx.curp.ui.wizard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import tec.mx.curp.GlobalProvider
import tec.mx.curp.components.Footer
import tec.mx.curp.components.Header
import tec.mx.curp.domain.nav.Screens
import tec.mx.curp.R

//Pantalla principal con las dos opciones
@Composable
fun HomeScreen() {
    val navController = GlobalProvider.current.nav
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()

        ) {
            Header(stringResource(R.string.title_home), stringResource(R.string.subtitle_home))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = stringResource(R.string.help_text_home),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(28.dp))
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(horizontal = 10.dp),
                    onClick = { navController.navigate(Screens.StepInstructionsScreen.generateRoute(true)) }
                ) {
                    Text(text = stringResource(R.string.mode_wizard))
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(horizontal = 10.dp),
                    onClick = { navController.navigate(Screens.Form.generateRoute(true)) }
                ) {
                    Text(text = stringResource(R.string.mode_fast))
                }
            }

        }
        Footer()
    }
}