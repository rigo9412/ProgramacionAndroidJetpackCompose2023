package com.almy.mochiapp.screens.AssigmentsDetails

import android.annotation.SuppressLint
import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.almy.mochiapp.R
import com.almy.mochiapp.models.Activity
import com.almy.mochiapp.models.Assigment
import com.almy.mochiapp.screens.CreateTask.ScreenUiState
import com.almy.mochiapp.screens.MainSreen.LoadingScreen
import com.almy.mochiapp.screens.MainSreen.MainScreen
import com.almy.mochiapp.screens.MainSreen.MainViewModel
import com.almy.mochiapp.ui.theme.*

@Composable
fun OnScreenTarea(
    assigmentID: String,
    viewModel: MainViewModel
) {
    val state = viewModel.uiState.collectAsState().value
    viewModel.getAssigment(assigmentID)

    when (state) {
        is ScreenUiState.Loading -> { LoadingScreen() }
        is ScreenUiState.Ready -> {
            ScreenTarea(assigmentID = assigmentID, viewModel = viewModel)
        }
        else -> {}
    }
}

@Composable
fun ScreenTarea(
    assigmentID: String,
    viewModel: MainViewModel
) {
    val data = viewModel.uiStateData.collectAsState().value
    //viewModel.getAssigment(assigmentID)

    val assigment = data.currentAssigment

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(25.dp)
    ) {
        listaActividades(assigment.titleAssigment, assigment.activities, viewModel)
        progreso(porcentaje = assigment.progress?.toFloat())
        integrantes(listaIntegrantes = assigment.members)
        notas(listNotes = assigment.notes, viewModel)
        botones( onClickAction =  {viewModel.updateAssigment(assigmentID)} )
    }
}

@Composable
fun listaActividades(
    title: String?,
    listActivities: List<Activity>?,
    viewModel: MainViewModel
) {
    Box(
        Modifier
            .fillMaxWidth()
            //.height(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cajaActividades)
            .padding(25.dp)
    ) {
        Column() {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.Group,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(35.dp),
                    contentDescription = "",
                    tint = Purple500
                )
                Text(
                    text = title ?: "",
                    fontSize = 25.sp,
                    color = textoActividades,
                    modifier = Modifier.padding(5.dp)
                )
            }

            Text(
                text = stringResource(id = R.string.detallesTarea1),
                fontSize = 20.sp,
                color = textoActividades,
                modifier = Modifier.padding(5.dp)
            )

            listActivities?.forEachIndexed { index, elemento ->
                actividad(
                    texto = elemento.nameActivity ?: "",
                    hecho = elemento.done ?: false,
                    index = index,
                    viewModel = viewModel
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun actividad(
    texto: String,
    hecho: Boolean,
    index: Int,
    viewModel: MainViewModel
) {
    val data = viewModel.uiStateData.collectAsState().value
    var done = remember { mutableStateOf(hecho) }
    Row(Modifier.padding(10.dp)) {
        /*Icon(
            Icons.Default.CheckCircle,
            modifier = Modifier
                .padding(end = 10.dp, top = 2.dp)
                .size(25.dp)
                .clickable { done.value = !done.value },
            contentDescription = "",
            tint = if (done.value) realizado else noRealizado
        )*/
        Checkbox(
            checked = done.value,
            onCheckedChange = {
                done.value = it
                viewModel.marcarCasillaActividad(index, done.value)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = realizado,
                uncheckedColor = noRealizado
            ),
        )
        Text(
            text = texto,
            fontSize = 17.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun progreso(
    porcentaje: Float?
) {
    Column(
        Modifier.padding(15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.detalleTarea2) + "${porcentaje?.toInt()}%",
            modifier = Modifier,
            fontSize = 20.sp,
            color = textoActividades
        )
        LinearProgressIndicator(
            progress = ((porcentaje ?: 0f) / 100),
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .height(10.dp)
        )
    }
}

@Composable
fun integrantes(
    listaIntegrantes: List<String>?
) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(cajaActividades)
            .padding(25.dp)
    ) {
        Column() {
            Text(
                text = stringResource(id = R.string.detalleTarea3),
                color = textoActividades,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            listaIntegrantes?.forEach {
                Text(
                    text = it,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }
        }
    }
}

@Composable
fun notas(
    listNotes: MutableList<String>?,
    viewModel: MainViewModel
) {
    var listaNotas = remember { mutableStateOf(listNotes ?: mutableListOf("")) }
    var note by remember { mutableStateOf("") }

    Column(
        Modifier.padding(vertical = 15.dp)
    ) {
        // Campo de entrada para tarea
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text(stringResource(id = R.string.detalleTarea4)) },
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 10.dp)
            )

            // Botón con icono de más para agregar tareas a la lista
            IconButton(
                onClick = {
                    if (note.isNotBlank()) {
                        listaNotas.value = (listaNotas.value + note) as MutableList<String>
                        viewModel.agregarNota(note)
                        note = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(cajaActividades)
                .padding(25.dp)
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.detalleTarea5),
                        color = textoActividades,
                        fontSize = 20.sp
                    )
                }

                listaNotas.value.forEachIndexed { index, element ->
                    var nota = remember { mutableStateOf(TextFieldValue(element)) }
                    Row(modifier = Modifier.padding(bottom = 5.dp)) {
                        Column() {
                            Text(
                                text = "•",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(15.dp)
                            )
                        }
                        Column() {
                            TextField(
                                value = nota.value,
                                onValueChange = { newText ->
                                    listaNotas.value[index] = newText.text
                                    nota.value = newText
                                    viewModel.modificarNota(index, newText.text)
                                },
                                colors = TextFieldDefaults.textFieldColors(backgroundColor = cajaActividades)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun botones(
    onClickAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { onClickAction() },
            modifier = Modifier.width(100.dp)
        ) {
            Text(stringResource(id = R.string.detalleTarea6))
        }
    }
}