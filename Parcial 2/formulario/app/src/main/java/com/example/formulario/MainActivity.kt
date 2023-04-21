package com.example.formulario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formulario.ui.theme.CustomInput
import com.example.formulario.ui.theme.FormularioTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormularioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                   NavHost(navController=navController, startDestination = rutas){
//                   }
                }
            }
        }
    }
}

@Composable
fun pantalla(){
    val focusManager = LocalFocusManager.current
    var name by remember{ mutableStateOf("") }
    var name1 by remember{ mutableStateOf("") }
    var name2 by remember{ mutableStateOf("") }
    var fecha by remember{ mutableStateOf("") }
    var sexo by remember{ mutableStateOf("") }
    var state by remember{ mutableStateOf("") }
    var expanded by remember{ mutableStateOf(false) }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            CustomInput(label = "Nombre(s)",
            value=name,
            onChangeValue={
//                          if(it.matches()){
//                              name = it.uppercase()
//                          }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager=focusManager)

        CustomInput(label = "primer apellido",
                value=name,
            onChangeValue={name = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager=focusManager)

        CustomInput(label = "segundo apellido",
            value=name,
            onChangeValue={name = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager=focusManager)
    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FormularioTheme {
        pantalla()
    }
}