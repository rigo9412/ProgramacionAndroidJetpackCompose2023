package com.almy.mochiapp.screens.CreateAccountScreen

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.almy.mochiapp.screens.CreateAccountScreen.components.*
import com.almy.mochiapp.screens.CreateAccountScreen.components.Image
import com.almy.mochiapp.screens.LoginScreen.LoginScreen
import com.almy.mochiapp.screens.LoginScreen.LoginUiState
import com.almy.mochiapp.screens.LoginScreen.LoginViewModel
import com.almy.mochiapp.screens.LoginScreen.components.CustomInput
import com.almy.mochiapp.screens.MenuSplashScreen.MenuSplashViewModel
import com.almy.mochiapp.ui.theme.WhiteDark
import com.almy.mochiapp.R


@Composable
fun CreateAccountScreen(viewModel: LoginViewModel, navController: NavController){
    val state = viewModel.loginUiState.collectAsState().value

    when(state){
        is LoginUiState.LoginScreen -> LoginScreen(viewModel = viewModel, navController)
        is LoginUiState.CrearCuentaScreen -> CreateAccount(viewModel = viewModel, navController)
        else -> {
            //TODO
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CreateAccount(viewModel: LoginViewModel, navController: NavController){
    val state = viewModel.uiStateLogin.collectAsState().value

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(WhiteDark)
    ) {
        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .size(350.dp, 250.dp)
        ){
            Image(ruta = "images/MochiPNG.png",modifier = Modifier.fillMaxSize())
        }

        Column(modifier = Modifier
            .padding(10.dp)
            .shadow(4.dp)
            .fillMaxSize()
            .background(Color.White)
        ){
            CustomTextTitle(
                text = stringResource(id = R.string.login5),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            CustomTextSubtitle(
                text = stringResource(id = R.string.login1),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Image(
                ruta = "images/LoginUsuario.png",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )

            CustomInput(
                stringResource(id = R.string.login2),
                state.email,
                Modifier
                    .width(250.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally)
            ){
                viewModel.onNameChanged(it)
            }

            Spacer(modifier = Modifier.padding(10.dp))
            CustomInput(
                stringResource(id = R.string.login3),
                state.password,
                Modifier
                    .width(250.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally)
            ){
                viewModel.onPasswordChanged(it)
            }

            /*Spacer(modifier = Modifier.padding(10.dp))
            CustomTextSubtitle(
                text = stringResource(id = R.string.login7),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Image(
                ruta = "images/LoginHuella.png",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )
            CustomTextHuella(
                text = stringResource(id = R.string.login8),
                isDetected = state.isDetected,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )*/

            Spacer(modifier = Modifier.padding(10.dp))

            CreateAccountButton(
                state.isValidPassword && state.isValidEmail,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(150.dp)
                    .height(40.dp)
            ) {
                //viewModel.onLoginSelected()
                //TRATAMOS DE CREAR LA CUENTA
                viewModel.createUserWithEmailAndPassword(){
                    navController.navigate("pantallas"){
                        popUpTo(0)
                    }
                }
            }

            CustomTextClickeable(text = stringResource(id = R.string.login9)) {
                viewModel.LoginScreen()
            }
        }
    }
}