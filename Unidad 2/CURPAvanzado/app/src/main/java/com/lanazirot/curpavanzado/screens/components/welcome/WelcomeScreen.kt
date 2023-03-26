package com.lanazirot.curpavanzado.screens.components.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.provider.LocalGlobalProvider
import dagger.hilt.internal.aggregatedroot.codegen.theme.BANNER_GOBIERNO_COLOR


@Composable
fun WelcomeScreen() {

    val gp = LocalGlobalProvider.current

    val navController = gp.nav

    LaunchedEffect(Unit){ gp.wizardVM.resetPerson() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = com.lanazirot.curpavanzado.R.drawable.gobiernodemexico),
            contentDescription = "CURP Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(Routes.WizardName.route)
            },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BANNER_GOBIERNO_COLOR,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = stringResource(id = com.lanazirot.curpavanzado.R.string.welcome_screen_button_wizard))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(Routes.Manual.route)
            },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BANNER_GOBIERNO_COLOR,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = stringResource(id = com.lanazirot.curpavanzado.R.string.welcome_screen_button_manual))
        }
    }
}

