package com.game.curp.ui.wizard.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.East
import androidx.compose.material.icons.filled.Start
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun StepLayout(
    isLast: Boolean = false,
    isFirst: Boolean = false,
    title: String,
    subtitle: String,
    content: @Composable (PaddingValues) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            TitleTopBar(
                title = title,
                onBackAction = onBack,
                isFirst = isFirst,
                subTitle = subtitle
            )
        },
        content = content,

        floatingActionButton = {
            if(isLast){
                ExtendedFloatingActionButton(
                    text = { Text("Terminar", color = Color.Black) },
                    icon = {
                        Icon(
                            Icons.Filled.Done ,
                            contentDescription = "Siguiente"
                        )
                    },
                    onClick = {
                        onSubmit()
                    })
            }
            else if(isFirst){
                ExtendedFloatingActionButton(
                    text = { Text("Iniciar", color = Color.Black) },
                    icon = {
                        Icon(
                            Icons.Filled.Start ,
                            contentDescription = "INICIAR"
                        )
                    },
                    onClick = {
                        onSubmit()
                    })
            }
            else {
                ExtendedFloatingActionButton(
                    text = { Text(text = "Siguiente", color = Color.Black) },
                    icon = {
                        Icon(
                            Icons.Filled.East ,
                            contentDescription = "Generar"
                        )
                    },
                    onClick = {
                        onSubmit()
                    }
                )
            }
        }
    )
}