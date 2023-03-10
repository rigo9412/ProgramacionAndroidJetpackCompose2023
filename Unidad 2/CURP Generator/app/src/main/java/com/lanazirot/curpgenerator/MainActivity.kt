package com.lanazirot.curpgenerator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.lanazirot.curpgenerator.screens.curp.components.CURPScreen
import com.lanazirot.curpgenerator.ui.theme.CURPGeneratorTheme
import com.lanazirot.curpgenerator.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CURPMainApp()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CURPMainApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CURP Generador", color = Color.White) }, backgroundColor = Color(
                    Purple500.value
                )
            )
        },
        content = {
            CURPScreen()
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CURPGeneratorTheme {
        CURPMainApp()
    }
}