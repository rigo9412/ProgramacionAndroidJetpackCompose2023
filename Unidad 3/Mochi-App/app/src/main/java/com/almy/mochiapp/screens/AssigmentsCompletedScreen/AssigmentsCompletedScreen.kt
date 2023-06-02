package com.almy.mochiapp.screens.AssigmentsCompletedScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.almy.mochiapp.R
import com.almy.mochiapp.screens.CreateAccountScreen.components.*
import com.almy.mochiapp.ui.theme.WhiteDark


@Composable
fun AssigmentsCompletedScreen(viewModel: AssigmentsCompletedViewModel, navController: NavController){
    val state = viewModel.assigmentsCompletedUiState.collectAsState().value

    when(state){
        is AssigmentsCompletedUiState.AssigmentsCompletedScreen -> AssigmentsCompleted(viewModel = viewModel, navController)
        else -> {
            //TODO
        }
    }
}

@Composable
fun AssigmentsCompleted(viewModel: AssigmentsCompletedViewModel, navController: NavController) {
    val state = viewModel.uiStateAssigmentsCompleted.collectAsState().value
    val tareasIndividuales = listOf(
        "Proyecto de integracion de mercados",
        "Realizar jercicios de iOS",
        "Investigacion de operaciones",
        "Verficar maestros para asesorias",
        "Estudiar para Android")

    val tareasGrupales = listOf(
        "Practicar ejercicios de Metodos Numericos",
        "Disfrutar vacaciones",
        "Verificar avances de Android",
        "Realizar practicas de administracion de redes",
        "Instalar maquina virtual con macOS")

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(WhiteDark)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .shadow(4.dp)
                .fillMaxSize()
                .background(Color.White)
        ) {
            CustomTextTitle(
                text = "TAREAS COMPLETADAS",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            CustomTextSubtitle(
                text = stringResource(id = R.string.tareaCompletada1),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(10.dp))


            for (tarea in tareasIndividuales) {
                CustomTextAssigmentCompleted(text = tarea, modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.padding(10.dp))
            }

            Spacer(modifier = Modifier.padding(15.dp))

            CustomTextSubtitle(
                text = stringResource(id = R.string.tareaCompletada2),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(10.dp))

            for(tarea in tareasGrupales){
                CustomTextAssigmentCompleted(text = tarea, modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.padding(10.dp))
            }

        }
    }
}

