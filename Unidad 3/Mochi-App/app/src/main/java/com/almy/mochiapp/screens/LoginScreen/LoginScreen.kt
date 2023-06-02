package com.almy.mochiapp.screens.LoginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.almy.mochiapp.screens.CreateAccountScreen.CreateAccountScreen
import com.almy.mochiapp.screens.CreateAccountScreen.components.CustomTextSubtitle
import com.almy.mochiapp.screens.LoginScreen.components.*
import com.almy.mochiapp.ui.theme.LightPurple
import com.almy.mochiapp.R


@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    val state = viewModel.loginUiState.collectAsState().value

    when(state){
        is LoginUiState.LoginScreen -> Login(viewModel, navController)
        is LoginUiState.CrearCuentaScreen -> CreateAccountScreen(viewModel, navController)
        else -> {
            //TODO
        }
    }
}

@Composable
fun Login(viewModel: LoginViewModel, navController: NavController) {
    val data = viewModel.uiStateLogin.collectAsState().value
    viewModel.onLoginSelected()

    Column(modifier= Modifier
        .fillMaxWidth()
        .background(Color.White)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ){
        Image("images/loginMochi.png",
            Modifier
                .align(Alignment.CenterHorizontally)
                .size(400.dp)
        )
        CustomTextSubtitle(text = stringResource(id = R.string.login1), modifier = Modifier.align(Alignment.CenterHorizontally))
        Image(ruta = "images/LoginUsuario.png",
            Modifier
                .align(Alignment.CenterHorizontally)
                .size(150.dp))

        CustomInput(
            stringResource(id = R.string.login2), data.email,
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(250.dp)
                .height(60.dp)
        ){
            viewModel.onNameChanged(it)
        }

        Spacer(modifier = Modifier.padding(5.dp))
        CustomInput(
            stringResource(id = R.string.login3), data.password,
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(250.dp)
                .height(60.dp)
        ){
            viewModel.onPasswordChanged(it)
        }

        /*Spacer(modifier = Modifier.padding(15.dp))
        Image(ruta = "images/LoginHuella.png", modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .size(90.dp))

        CustomTextHuella(
            text = stringResource(id = R.string.login8),
            isDetected = data.isDetected,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )*/


        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(data.isValidPassword && data.isValidEmail,
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(150.dp)
                .height(40.dp)
        ){
            viewModel.onLoginSelected()

            //TRATAMOS DE INICIAR SESION
            viewModel.signInWithEmailAndPassword(){
                navController.navigate("pantallas"){
                    popUpTo(0)
                }
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))
        CustomTextClickeable(text = stringResource(id = R.string.login5)){
            viewModel.CrearCuentaScreen()
        }
    }
}

@Composable
fun LoginTest(viewModel: LoginViewModel) {
    val data = viewModel.uiStateLogin.collectAsState().value
    viewModel.onLoginSelected()

    Column(modifier= Modifier
        .fillMaxWidth()
        .background(Color.White)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ){
        Image("images/loginMochi.png",
            Modifier
                .align(Alignment.CenterHorizontally)
                .size(400.dp)
        )
        CustomTextSubtitle(text = stringResource(id = R.string.login1), modifier = Modifier.align(Alignment.CenterHorizontally))
        Image(ruta = "images/LoginUsuario.png",
            Modifier
                .align(Alignment.CenterHorizontally)
                .size(150.dp))

        CustomInput(
            stringResource(id = R.string.login2), data.email,
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(250.dp)
                .height(60.dp)
                .testTag("lblEmail")
        ){
            viewModel.onNameChanged(it)
        }

        Spacer(modifier = Modifier.padding(5.dp))
        CustomInput(
            stringResource(id = R.string.login3), data.password,
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(250.dp)
                .height(60.dp)
        ){
            viewModel.onPasswordChanged(it)
        }

        /*Spacer(modifier = Modifier.padding(15.dp))
        Image(ruta = "images/LoginHuella.png", modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .size(90.dp))

        CustomTextHuella(
            text = stringResource(id = R.string.login8),
            isDetected = data.isDetected,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )*/


        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(data.isValidPassword && data.isValidEmail,
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(150.dp)
                .height(40.dp)
        ){
            viewModel.onLoginSelected()

            //TRATAMOS DE INICIAR SESION
            viewModel.signInWithEmailAndPassword(){}
        }

        Spacer(modifier = Modifier.padding(16.dp))
        CustomTextClickeable(text = stringResource(id = R.string.login5)){
            viewModel.CrearCuentaScreen()
        }
    }
}