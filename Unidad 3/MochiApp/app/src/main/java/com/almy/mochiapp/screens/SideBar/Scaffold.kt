package com.myriam.pantallalateral

import android.annotation.SuppressLint
import android.graphics.fonts.Font
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.almy.mochiapp.NavigationGraph2
import com.almy.mochiapp.R
import com.almy.mochiapp.firebase.auth
import com.almy.mochiapp.firebase.getCurrentUserName
import com.almy.mochiapp.screens.AssigmentsCompletedScreen.AssigmentsCompletedViewModel
import com.almy.mochiapp.screens.CreateTask.AssigmentViewModel
import com.almy.mochiapp.screens.MainSreen.MainViewModel
import com.almy.mochiapp.screens.MenuSplashScreen.MenuSplash
import com.almy.mochiapp.screens.MenuSplashScreen.MenuSplashScreen
import com.almy.mochiapp.screens.MenuSplashScreen.MenuSplashViewModel
import com.almy.mochiapp.screens.MenuSplashScreen.components.Image
import com.almy.mochiapp.ui.theme.LightPurple
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timerTask

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllScreens(
    navControllerLogin: NavController,
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val loading = remember { mutableStateOf(true) }

    if(loading.value)
    {
        Box(
            modifier = Modifier.fillMaxWidth().fillMaxSize().background(LightPurple),
            contentAlignment = Alignment.Center
        ) {
            Image("images/logotipo.png")
        }

        Timer().schedule(timerTask {
            loading.value = false
        }, 2000)
    } else{

        Scaffold(
            topBar = {
                MyTopBar(
                    onClickIcon = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "has pulsado $it"
                            )
                        }
                    },
                    onClickDrawer = { coroutineScope.launch { scaffoldState.drawerState.open() } }
                )
            },
            scaffoldState = scaffoldState,
            //bottomBar = { MyBottomNavigation() },
            floatingActionButton = { MyFAB(navController) },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = false,
            drawerContent = {
                MyDrawer(navController, navControllerLogin) {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                }
            },
            drawerGesturesEnabled = false
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationGraph2(
                    navController = navController,
                    navControllerLogin = navControllerLogin
                )
            }
        }

    }
}


@Composable
fun MyTopBar(
    //topBarState: MutableState<Boolean>,
    onClickIcon: (String) -> Unit,
    onClickDrawer: () -> Unit
) {
    TopAppBar(title = { Text(text = "Mochi APP") },
        backgroundColor = Color(0xFFF5A4B9D),
        contentColor = Color.White,
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
            }
        },
        actions = {
/*
           IconButton(onClick = { onClickIcon("Buscar") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "back")
            }

            IconButton(onClick = { onClickIcon("Favorito") }) {
                Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = "back")
            }
*/
        }
    )
}

@Composable
fun MyBottomNavigation() {
    var index by remember { mutableStateOf(0) }
    BottomNavigation(backgroundColor = Color(0xFFF5A4B9D), contentColor = Color.White) {
        BottomNavigationItem(selected = index == 0, onClick = { index = 0 }, icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "home"
            )
        }, label = { Text(text = "Home") })

        BottomNavigationItem(selected = index == 1, onClick = { index = 1 }, icon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "AGregar"
            )
        }, label = { Text(text = "Agregar") })

        BottomNavigationItem(selected = index == 2, onClick = { index = 2 }, icon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Persona"
            )
        }, label = { Text(text = "Persona") })
    }
}

@Composable
fun MyFAB( navController: NavController)
{
    FloatingActionButton(
        onClick = { navController.navigate("crearnotas") },
        backgroundColor = LightPurple,
        contentColor = Color.Black
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
    }
}

@Composable
fun MyDrawer(
    navController: NavController,
    navControllerLogin: NavController,
    onCloseDrawer: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color(0xfff7663BA)),
        //verticalArrangement = Arrangement.SpaceAround,
        //horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(

            modifier = Modifier
                .background(Color(0xFFF5A4B9D))
                .fillMaxWidth()
                .height(60.dp)
                .border(2.dp, Color(0xfff7663BA))
                .padding(vertical = 8.dp)
                .clickable {
                    navController.navigate("inicio")
                    onCloseDrawer()
                }
                .clip(RoundedCornerShape(12))
                .padding(13.dp)
        ) {
            Text(
                text = stringResource(id = R.string.lateral3),
                color = Color.White,
                fontWeight = Bold,
                fontFamily = FontFamily.Monospace
            )
            Icon(

                imageVector = Icons.Default.List,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(80.dp)
                    .padding(start = 10.dp)

            )
        }
        /*Row(

            modifier = Modifier
                .background(Color(0xFFF5A4B9D))
                .fillMaxWidth()
                .height(60.dp)
                // .border(2.dp,Color(0xfff7663BA))
                .padding(vertical = 8.dp)
                .clickable {
                    navController.navigate("usuario")
                    onCloseDrawer()
                }
                .clip(RoundedCornerShape(12))
                .padding(13.dp)
            // .border(width = 5.dp, brush = Brush.horizontalGradient(), shape = RoundedCornerShape(12.dp))
        ) {

            Text(
                text = "Usuario",
                color = Color.White,
                fontWeight = Bold,
                fontFamily = FontFamily.Monospace
            )
            Icon(

                imageVector = Icons.Default.People,
                contentDescription = "Usuario",
                tint = Color(0xfff9561C8),
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 10.dp)

            )

        }*/
        Row(

            modifier = Modifier
                .background(Color(0xFFF5A4B9D))
                .fillMaxWidth()
                .height(80.dp)
                .border(2.dp, Color(0xfff7663BA))
                .padding(vertical = 8.dp)
                .clickable {
                    navController.navigate("tareascompletadas")
                    onCloseDrawer()
                }
                .clip(RoundedCornerShape(12))
                .padding(13.dp)
        ) {
            Text(
                text = stringResource(id = R.string.lateral2),
                color = Color.White,
                fontWeight = Bold,
                fontFamily = FontFamily.Monospace
            )
            Icon(

                imageVector = Icons.Default.Checklist,
                contentDescription = "Cerrar Sesión",
                tint = Color.White,
                modifier = Modifier
                    .size(70.dp)
                    .padding(start = 20.dp)

            )
        }
        Row(

            modifier = Modifier
                .background(Color(0xFFF5A4B9D))
                .fillMaxWidth()
                .height(70.dp)
                .border(2.dp, Color(0xfff7663BA))
                .padding(vertical = 8.dp)
                .clickable {
                    navController.navigate("crearnotas")
                    onCloseDrawer()
                }
                .clip(RoundedCornerShape(12))
                .padding(13.dp)
        ) {
            Text(
                text = stringResource(id = R.string.crearTarea5),
                color = Color.White,
                fontWeight = Bold,
                fontFamily = FontFamily.Monospace
            )
            Icon(

                imageVector = Icons.Default.NoteAdd,
                contentDescription = "Cerrar Sesión",
                tint = Color.White,
                modifier = Modifier
                    .size(70.dp)
                    .padding(start = 10.dp)

            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(Color(0xfff7663BA)),
            verticalArrangement = Arrangement.Bottom
            //verticalArrangement = Arrangement.SpaceAround,
            //horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(

                modifier = Modifier
                    .background(Color(0xFFF5A4B9D))
                    .fillMaxWidth()
                    .height(60.dp)

                    .padding(vertical = 8.dp)
                    .clickable {
                        auth.signOut()
                        navControllerLogin.navigate("login") { popUpTo(0) }
                        navController.navigate("inicio") { popUpTo(0) }
                        onCloseDrawer()
                    }
                    .clip(RoundedCornerShape(12))
                    .padding(13.dp)
            ) {

                Text(
                    text = stringResource(id = R.string.lateral1),
                    color = Color.White,
                    fontWeight = Bold,
                    fontFamily = FontFamily.Monospace
                )
                Icon(

                    imageVector = Icons.Default.Logout,
                    contentDescription = "Cerrar Sesión",
                    tint = Color.White,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(start = 10.dp)
                )
            }
        }
    }
}