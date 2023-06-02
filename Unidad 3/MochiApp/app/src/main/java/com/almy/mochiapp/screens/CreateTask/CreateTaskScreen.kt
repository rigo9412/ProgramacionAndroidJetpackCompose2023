package com.almy.mochiapp.screens.CreateTask

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.almy.mochiapp.ui.theme.MochiAppTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.almy.mochiapp.R
import com.almy.mochiapp.firebase.currentUser
import com.almy.mochiapp.firebase.getCurrentUserName
import com.almy.mochiapp.models.Activity
import com.almy.mochiapp.screens.MainSreen.LoadingScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@Composable
fun CreateTaskScreen(viewModel: AssigmentViewModel) {
    val state = viewModel.uiState.collectAsState().value

    when (state) {
        is ScreenUiState.Loading -> { LoadingScreen() }
        is ScreenUiState.Done -> { AssigmentScreen(viewModel) }
        else -> {}
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AssigmentScreen(viewModel: AssigmentViewModel) {
    val data = viewModel.uiStateData.collectAsState().value
    var context = LocalContext.current

    var taskName by remember { mutableStateOf("") }
    var activityName by remember { mutableStateOf("") }
    var taskList by remember { mutableStateOf(listOf<String>()) }
    var memberName by remember { mutableStateOf("") }
    var memberList by remember { mutableStateOf(listOf<String>("${data.currentUserName}")) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.crearTarea1),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Campo de entrada para el nombre de la tarea
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text(stringResource(id = R.string.crearTarea2))},
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de entrada para tarea
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = activityName,
                onValueChange = { activityName = it },
                label = { Text(stringResource(id = R.string.crearTarea8)) },
                modifier = Modifier.weight(1f)
            )

            // Botón con icono de más para agregar tareas a la lista
            IconButton(
                onClick = {
                    if (activityName.isNotBlank()) {
                        taskList = taskList + activityName
                        activityName = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }

        // Mostrar la lista de tareas en una tarjeta
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            elevation = 4.dp,
            backgroundColor = Color.White
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = R.string.crearTarea6),
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                taskList.forEach { task ->
                    Text(text = "- $task")
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*OutlinedTextField(
                value = memberName,
                onValueChange = { memberName = it },
                label = { Text("Agregar usuario") },
                modifier = Modifier.weight(1f)
            )*/

            var open by remember{ mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = open,
                onExpandedChange = { open = it}
            ) {
                TextField(
                    value = memberName,
                    label = { Text(text = stringResource(id = R.string.crearTarea4))},
                    onValueChange = { memberName = it },
                    modifier = Modifier.weight(1f),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = open)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    readOnly = true
                )
                if(data.userNames?.isNotEmpty() ?: false){
                    ExposedDropdownMenu(
                        expanded = open,
                        onDismissRequest = { open = false}
                    ) {
                        data.userNames?.forEach{ selectedOption ->
                            DropdownMenuItem(
                                onClick = {
                                    memberName = selectedOption ?: ""
                                    open = false
                                }
                            ) {
                                Text(text = selectedOption ?: "")
                            }
                        }
                    }
                }
            }

            // Botón con icono de más para
            IconButton(
                onClick = {
                    if (memberName.isNotBlank()) {
                        if(memberList.contains(memberName))
                        {
                            //snackbarVisible = true
                            Toast.makeText(
                                context, "Este usuario ya ha sido agregado...",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else{
                            memberList = memberList + memberName
                            memberName = ""
                        }
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar usuario")
            }
        }

        // Mostrar la lista de tareas en una tarjeta
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 4.dp,
            backgroundColor = Color.White
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = R.string.crearTarea7),
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                memberList.forEachIndexed { index, member ->
                    Text(text = "- $member", modifier = Modifier.testTag("lblMember$index"))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            // Botón para agregar tarea a la lista
            Button(
                onClick = {
                    var activities: MutableList<Activity> = mutableListOf()
                    taskList.forEach {
                        activities.add(Activity("", it, false))
                    }

                    viewModel.getCurrentUserName(
                        memberList = memberList,
                        titleAssigment = taskName,
                        activities = activities,
                    )
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.crearTarea11))
            }

            // Botón para regresar sin agregar tarea
            /*Button(
                onClick = { isAddingTask = false },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = "Regresar")
            }*/
        }
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Text(text = "Cargando...", modifier = Modifier.padding(top = 10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MochiAppTheme {
        //CreateTaskScreen()
    }
}
