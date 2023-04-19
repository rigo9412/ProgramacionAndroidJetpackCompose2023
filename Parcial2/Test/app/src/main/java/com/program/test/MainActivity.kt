package com.program.test

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.service.controls.templates.ControlButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.program.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Hello World"){}
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String,modifier: Modifier = Modifier,clickAction: (Int) -> Unit) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = name,modifier=Modifier)
        ControlButtons(clickAction)
    }

}

@Composable
fun ControlButtons(clickAction: (Int)->Unit){
    Row(
        modifier=Modifier.fillMaxWidth()
    ) {
        Button(
            modifier=Modifier.testTag("button_add"),
            onClick = { clickAction(1) }
        ) {
            Text(text = "Incrementar")
        }
        Button(
            modifier=Modifier.testTag("button_remove"),
            onClick = { clickAction(-1) }
        ) {
            Text(text = "Reducir")
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestTheme {
        Greeting("Android")
    }
}*/