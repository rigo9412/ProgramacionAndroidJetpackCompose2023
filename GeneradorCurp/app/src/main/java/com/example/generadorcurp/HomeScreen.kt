package com.example.generadorcurp

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
import com.example.generadorcurp.components.Footer
import com.example.generadorcurp.components.Header
import com.example.generadorcurp.ui.global.GlobalProvider
import com.example.generadorcurp.ui.nav.Screens

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
            Header("Generador de CURP", "No oficial")

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Escoga una opcion",
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
                    Text(text = "Wizard")
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(horizontal = 10.dp),
                    onClick = { navController.navigate(Screens.Form.generateRoute(true)) }
                ) {
                    Text(text = "Viewmodel")
                }
            }

        }
        Footer()
    }
}