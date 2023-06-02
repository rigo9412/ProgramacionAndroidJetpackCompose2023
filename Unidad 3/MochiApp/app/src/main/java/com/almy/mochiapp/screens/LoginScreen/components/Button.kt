package com.almy.mochiapp.screens.LoginScreen.components


import androidx.compose.material.Button

import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import com.almy.mochiapp.R
import com.almy.mochiapp.ui.theme.InputPurpleColor
import com.almy.mochiapp.ui.theme.LightPurple
import com.almy.mochiapp.ui.theme.WhiteSmoke

@Composable
fun LoginButton(enabled: Boolean,modifier: Modifier, onLoginSelected: () -> Unit){
    Button(
        enabled = enabled,
        onClick = {onLoginSelected()},
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = InputPurpleColor, contentColor = Color.White, disabledBackgroundColor = LightPurple )
    ) {
        Text(text = stringResource(id = R.string.login4), color = WhiteSmoke)
    }
}

