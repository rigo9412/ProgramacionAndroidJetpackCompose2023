package com.almy.mochiapp.screens.MainSreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.almy.mochiapp.R
import com.almy.mochiapp.firebase.auth
import com.almy.mochiapp.firebase.getCurrentUserName
import com.almy.mochiapp.models.Assigment
import com.almy.mochiapp.screens.AssigmentsDetails.ScreenTarea
import com.almy.mochiapp.screens.CreateTask.ScreenUiState
import com.almy.mochiapp.screens.LoadingAnimation.PantallaDeCarga
import com.almy.mochiapp.ui.theme.*


@Composable
fun OnMainScreen(
    navController: NavController,
    viewModel: MainViewModel,
    navControllerLogin: NavController
) {
    val state = viewModel.uiState.collectAsState().value

    when (state) {
        is ScreenUiState.Loading -> { LoadingScreen() }
        is ScreenUiState.Ready -> {
            MainScreen(
                navController = navController,
                viewModel = viewModel,
                navControllerLogin = navControllerLogin
            )
        }
        else -> {}
    }
}

@Composable
fun LoadingScreen()
{
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PantallaDeCarga()
        Text(text = stringResource(id = R.string.crearTarea10), modifier = Modifier.padding(top = 10.dp))
    }
}


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel,
    navControllerLogin: NavController
) {
    //viewModel.listenPostAssigments()
    //var listAssigment: List<Assigment> = listOf()
    /*if (navControllerLogin.currentDestination?.route == "pantallas") {
        /*listAssigment = */viewModel.getUserAssigments()
    }*/
    val data = viewModel.uiStateData.collectAsState().value
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.main1) + " ${data.currentUserName}!",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 15.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.main6),
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 15.dp)
        )

        data.listUserAssigments.forEach {
            CardExample(
                Color1 = Color(0xFF9F77F1),
                Color2 = Color(0xFFD0A6F5),
                TextoTitulo = it.titleAssigment ?: "",
                TextoTarea = it.activities?.get(0)?.nameActivity ?: "",
                TextoCreador = stringResource(id = R.string.main7) + "${it.createdBy}",
                creador = it.createdBy ?: "",
                onClickMostrar = { navController.navigate("vertarea/${it.id}") },
                onClickEliminar = { viewModel.deleteAssigment(it.id ?: "") },
                viewModel = viewModel
            )
        }
    }
}


@Composable
fun CardExample(
    Color1: Color,
    Color2: Color,
    TextoTitulo: String,
    TextoTarea: String,
    TextoCreador: String,
    creador: String,
    onClickMostrar: () -> Unit,
    onClickEliminar: () -> Unit,
    viewModel: MainViewModel
) {
    val data = viewModel.uiStateData.collectAsState().value
    val openDialog = remember { mutableStateOf(false) }
    val esElCreador: MutableState<Boolean> = remember { mutableStateOf(false) }
    esElCreador.value = data.currentUserName == creador

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = stringResource(id = R.string.main4))
            },
            text = {
                Text(stringResource(id = R.string.main8) + "$TextoTitulo?")
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            onClickEliminar()
                            openDialog.value = false
                        },
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Purple500)
                    ) {
                        Text(stringResource(id = R.string.main9), color = Color.White)
                    }
                }

            },
            dismissButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 0.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            openDialog.value = false
                        },
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = btnCancelar)
                    ) {
                        Text(stringResource(id = R.string.main9), color = Color.White)
                    }
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
//        shape = RoundedCornerShape(16.dp),

        contentColor = Color(0xFF4E4E4E),


        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .height(200.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color1, Color2),
//                        startY = 0f,
//                        endY = 200f
                    )
                )
                .clip(RoundedCornerShape(20.dp)),

            ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = TextoTitulo,
                        style = MaterialTheme.typography.h5,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Icon(

                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 16.dp),

                        )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = TextoTarea,
                    style = MaterialTheme.typography.h6,
                    color = lblActividades,
                    //modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = TextoCreador,
                    style = MaterialTheme.typography.body2,
                    color = lblActividades
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        onClick = { openDialog.value = true },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(end = 8.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = btnTarea,
                            disabledBackgroundColor = btnCancelar
                        ),
                        enabled = esElCreador.value
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Eliminar",
                            tint = Color.White
                        )
                        Text(stringResource(id = R.string.main12), color = Color.White)
                    }

                    Button(
                        onClick = { onClickMostrar() },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(start = 5.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = btnTarea)
                    ) {
                        Icon(
                            Icons.Filled.Visibility,
                            contentDescription = "Mostrar",
                            tint = Color.White
                        )
                        Text(stringResource(id = R.string.main11), color = Color.White)
                    }
                }
            }
        }
    }
}


@Composable
fun AlertDialogSample() {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(false) }

            Button(onClick = {
                openDialog.value = true
            }) {
                Text("Click me")
            }

            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Dialog Title Will Show Here")
                    },
                    text = {
                        Text("Here is a description text of the dialog")
                    },
                    confirmButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Confirm Button")
                        }
                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Dismiss Button")
                        }
                    }
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MochiAppTheme {
        //InicioScreen()
    }
}