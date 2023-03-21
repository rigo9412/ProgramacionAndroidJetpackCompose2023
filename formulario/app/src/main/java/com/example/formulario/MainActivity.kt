package com.example.formulario

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.formulario.ui.theme.FormularioTheme
import java.time.format.DateTimeFormatter
import com.example.formulario.ui.theme.rutas
import com.example.formulario.ui.theme.CustomInput

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