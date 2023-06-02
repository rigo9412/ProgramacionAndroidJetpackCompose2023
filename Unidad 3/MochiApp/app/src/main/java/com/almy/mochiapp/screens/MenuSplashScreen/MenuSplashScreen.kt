package com.almy.mochiapp.screens.MenuSplashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.almy.mochiapp.screens.LoginScreen.LoginScreen
import com.almy.mochiapp.screens.LoginScreen.LoginViewModel
import com.almy.mochiapp.screens.MenuSplashScreen.components.Image
import com.almy.mochiapp.ui.theme.LightPurple


@Composable
fun MenuSplashScreen( viewModel: MenuSplashViewModel, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxSize().background(LightPurple),
        contentAlignment = Alignment.Center
    ) {
        MenuSplash(viewModel, navController)
    }
}

@Composable
fun MenuSplash( viewModel: MenuSplashViewModel, navController: NavController){
    val data = viewModel.uiStateMenuSplash.collectAsState().value

    if(data.menuSplashTimeSpan){
        LoginScreen(LoginViewModel(), navController)
    }
    else{
        Image("images/logotipo.png")
        viewModel.onMenuSplashLoading()
    }
}