package com.lanazirot.testingcomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.lanazirot.testingcomposable.ui.theme.TestingComposableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestingComposableTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String = "Hello world! ",
    counter: Int = 0,
    onAdd: (Int) -> Unit = {},
    onSubtract: (Int) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(text = "$name $counter", Modifier.testTag("txt_name"))
        OutlinedButton(onClick = { onAdd(1) }, Modifier.testTag("btn_add")) {
            Text(text = "Subir")
        }
        OutlinedButton(onClick = { onSubtract(1) }, Modifier.testTag("btn_subtract")) {
            Text(text = "Bajar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestingComposableTheme {
        Greeting("Android")
    }
}