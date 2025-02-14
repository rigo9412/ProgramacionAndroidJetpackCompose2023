package com.rigo9412.curp.ui

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
import androidx.navigation.NavHostController
import com.example.curp.R
import com.rigo9412.curp.GlobalProvider
import com.rigo9412.curp.components.Footer
import com.rigo9412.curp.components.Header
import com.rigo9412.curp.ui.nav.Screens

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