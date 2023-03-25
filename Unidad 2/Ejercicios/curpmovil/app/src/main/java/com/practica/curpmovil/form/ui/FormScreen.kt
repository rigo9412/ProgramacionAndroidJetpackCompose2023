package com.practica.curpmovil.form.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.practica.curpmovil.form.domain.sexos
import com.practica.curpmovil.form.ui.components.CustomInput
import com.practica.curpmovil.form.ui.components.DatePickerBirthDate
import com.practica.curpmovil.form.ui.components.DropdownStates


import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.practica.curpmovil.R
import com.practica.curpmovil.R.*
import com.practica.curpmovil.form.ui.components.RadioButtonGroupSex

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormWizardGender(viewModel: FormViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    var focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, content = {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

            RadioButtonGroupSex(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                items = sexos,
                selection = data.gender,
                onItemClick = { viewModel.onChangeGender(it) })
        }
    }, topBar = {
        TopAppBar(modifier = Modifier.wrapContentSize(Alignment.Center),title = { Text(text = "Generar Curp - Sexo") } ,navigationIcon = {


            IconButton(onClick = { viewModel.regresarPaginaPrincipal() }) {
                Icon(Icons.Filled.Home, contentDescription = "Pagina Principal")
            }
            Image(
                painter = painterResource(id = R.drawable.logoheader),
                contentDescription = ""
            )
        })

    }, floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.toDateBirthPage() }) {
            Icon(Icons.Filled.Done, contentDescription = "Genero")
        }
    }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormWizardState(viewModel: FormViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    var focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState, content = {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

            DropdownStates(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                selected = data.state,
                label = "Estado",
                listItems = data.statesList,
                onValueChange = { viewModel.onChangeState(it) }
            )
        }
    }, topBar = {
        TopAppBar(modifier = Modifier.wrapContentSize(Alignment.Center),title = { Text(text = "Generar Curp - Estado") }, navigationIcon = {
            IconButton(onClick = { viewModel.regresarPaginaPrincipal() }) {
                Icon(Icons.Filled.Home, contentDescription = "Pagina Principal")
            }
            Image(
                painter = painterResource(id = R.drawable.logoheader),
                contentDescription = ""
            )
        })


    }, floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.generateCurpToShow() }) {
            Icon(Icons.Filled.Done, contentDescription = "CURP")
        }
    }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormWizardBirthDate(viewModel: FormViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    var focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState, content = {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            DatePickerBirthDate(
                label = "Fecha de Nacimiento",
                value = data.birth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                focusManager = focusManager,
                onValueChange = { viewModel.onChangeBirth(it) })
        }
    }, topBar = {
        TopAppBar(modifier = Modifier.wrapContentSize(Alignment.Center),title = { Text(text = "Generar Curp - Fecha") }, navigationIcon = {
            IconButton(onClick = { viewModel.regresarPaginaPrincipal() }) {
                Icon(Icons.Filled.Home, contentDescription = "Pagina Principal")
            }
            Image(
                painter = painterResource(id = R.drawable.logoheader),
                contentDescription = ""
            )
        })


    }, floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.toStateWizard() }) {
            Icon(Icons.Filled.Done, contentDescription = "Estado")
        }
    }
    )
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormPaginaPrincipal(viewModel: FormViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    var focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, content =
    {
        //Make two buttons to navigate between the two screens, vertically and center, in a column, suing only Text and buttons
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido a la aplicación para generar tu CURP",
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = "Presiona el botón para comenzar",
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Button(onClick = { viewModel.toNameWizard() }) {
                Text(text = "Wizard Mode")
            }
            //otro boton pero para la forma completa
            Button(onClick = { viewModel.toFormCompleta() }) {
                Text(text = "Forma Completa")
            }
        }


    }, topBar = {
        TopAppBar(modifier = Modifier.wrapContentSize(Alignment.Center),title = { Text(text = "Generar Curp") }, navigationIcon = {
            IconButton(onClick = { viewModel.regresarPaginaPrincipal() }) {
                Icon(Icons.Filled.Home, contentDescription = "Pagina Principal")
            }
            Image(
                painter = painterResource(id = R.drawable.logoheader),
                contentDescription = ""
            )
        })


    }
    )
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormWizardName(viewModel: FormViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    var focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, content = {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

            CustomInput(
                label = "Nombre(s)",
                value = data.name,
                onChangeValue = { viewModel.onChangeName(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                focusManager = focusManager
            )
            CustomInput(
                label = "Apellido Paterno",
                value = data.middleName,
                onChangeValue = { viewModel.onChangeMiddleName(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                focusManager = focusManager
            )
            CustomInput(
                label = "Apellido Materno",
                value = data.lastName,
                onChangeValue = { viewModel.onChangeLastName(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                focusManager = focusManager
            )

        }
    }, topBar = {
        TopAppBar(modifier = Modifier.wrapContentSize(Alignment.Center),title = { Text(text = "Generar Curp - Nombre") }, navigationIcon = {
            IconButton(onClick = { viewModel.regresarPaginaPrincipal() }) {
                Icon(Icons.Filled.Home, contentDescription = "Pagina Principal")
            }
        })


    }, floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.toGenderWizard() }) {
            Icon(Icons.Filled.Done, contentDescription = "Genero")
        }
    }
    )
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowCurp(viewModel: FormViewModel) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .wrapContentHeight(Alignment.CenterVertically)
        .background(color = Color.Transparent), content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)

            ) {

                Text(
                    text = "CURP: ",
                    fontWeight = FontWeight(48),
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = viewModel.getCurp(),
                    fontWeight = FontWeight(36),
                    fontFamily = FontFamily.SansSerif
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Button(onClick = { viewModel.regresarPaginaPrincipal() }) {
                    Text(text = "Regresar")
                }
            }

        }


    }, topBar = {
        TopAppBar(
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)

        ) {
            Image(
                painter = painterResource(id = R.drawable.logoheader),
                contentDescription = ""
            )
        }
    }, bottomBar = {
//        BottomAppBar(
//            modifier = Modifier
//                .background(color = Color.Transparent)
//                .wrapContentSize(align = Alignment.Center)
//
//        ) {
        Image(

            painter = painterResource(id = R.drawable.footer),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.background),
            contentScale = ContentScale.Crop
        )
        //}
    })


}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormComplete(viewModel: FormViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    var focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    var name by remember { mutableStateOf("") }
    var name1 by remember {
        mutableStateOf("")
    }
    var name2 by remember {
        mutableStateOf("")
    }
    var fecha by remember {
        mutableStateOf("")
    }
    var sexo by remember {
        mutableStateOf(Pair<String, String>("", ""))
    }
    var state by remember {
        mutableStateOf(Pair<String, String>("", ""))
    }
    var expanded by remember {
        mutableStateOf("")
    }

    Scaffold(scaffoldState = scaffoldState, content = {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            CustomInput(
                label = "Nombre(s)",
                value = data.name,
                onChangeValue = { viewModel.onChangeName(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                focusManager = focusManager
            )
            CustomInput(
                label = "Apellido Paterno",
                value = data.middleName,
                onChangeValue = { viewModel.onChangeMiddleName(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                focusManager = focusManager
            )
            CustomInput(
                label = "Apellido Materno",
                value = data.lastName,
                onChangeValue = { viewModel.onChangeLastName(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                focusManager = focusManager
            )
            RadioButtonGroupSex(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                items = sexos,
                selection = data.gender,
                onItemClick = { viewModel.onChangeGender(it) })

            DatePickerBirthDate(
                label = "Fecha de Nacimiento",
                value = data.birth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                focusManager = focusManager,
                onValueChange = { viewModel.onChangeBirth(it) })
            DropdownStates(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                selected = data.state,
                label = "Estado",
                listItems = data.statesList,
                onValueChange = { viewModel.onChangeState(it) }
            )
        }
    }, topBar = {
        TopAppBar(title = { Text(text = "Generar Curp") })

    }, floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.generateCurpToShow() }) {
            Icon(Icons.Filled.Done, contentDescription = "Generar CURP")
        }
    }
    )
}

data class CurpUIModel(
    val isValid: Boolean = false,
    var curp: String = "",
    val name: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val birth: String = "",
    val gender: Pair<String, String> = Pair<String, String>("", ""),
    val state: Pair<String, String> = Pair<String, String>("", ""),
    val sexList: ArrayList<Pair<String, String>> = ArrayList(),
    val statesList: ArrayList<Pair<String, String>> = ArrayList()
)


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen(viewModel: FormViewModel) {
    val state = viewModel.uiState.collectAsState().value
    when (state) {
        FormViewModel.FormUiState.Empty -> Text(text = "vicio")
        is FormViewModel.FormUiState.Error -> Text(text = "error")
        is FormViewModel.FormUiState.Loaded -> FormComplete(viewModel)
        is FormViewModel.FormUiState.Loading -> Text(text = "cargando")
        is FormViewModel.FormUiState.Success -> ShowCurp(viewModel = viewModel)
        is FormViewModel.FormUiState.WizardName -> FormWizardName(viewModel = viewModel)
        is FormViewModel.FormUiState.WizardState -> FormWizardState(viewModel = viewModel)
        is FormViewModel.FormUiState.WizardBirth -> FormWizardBirthDate(viewModel = viewModel)
        is FormViewModel.FormUiState.WizardGender -> FormWizardGender(viewModel = viewModel)
        FormViewModel.FormUiState.MainMenu -> FormPaginaPrincipal(viewModel = viewModel)
        is FormViewModel.FormUiState.FormComplete -> FormComplete(viewModel)
    }
}