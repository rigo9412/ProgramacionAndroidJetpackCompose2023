package com.lanazirot.curpgenerator.screens.loading.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lanazirot.curpgenerator.screens.Routes
import com.lanazirot.curpgenerator.screens.loading.viewmodel.CURPLoadingViewModel
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun CURPLoadingScreen(
    text: String = "Loading...",
    onClick: () -> Unit = {},
    navController: NavController
) {

    LaunchedEffect(Unit){
        delay(1500)
        navController.navigate(Routes.CURP.route){
            popUpTo(Routes.LOADING.route){
                inclusive = true
                saveState = false
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                strokeWidth = 4.dp
            )
        }
    }
}

