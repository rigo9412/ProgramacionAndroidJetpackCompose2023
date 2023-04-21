package tec.mx.pruebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import tec.mx.pruebas.ui.theme.PruebasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var counter by remember { mutableStateOf(0) }
                    ControlButton(clickAction =     {
                        counter += it
                    })
                    Greeting(name = "HELLO WORLD $counter")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, onClick: (Int) -> Unit = {}) {
    Text(text = "$name")
}


@Composable
fun ControlButton(clickAction: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    )
    {
        Button(modifier = Modifier.testTag("button_add"), onClick = { clickAction(1) }) {
            Text(text = "Incrementar")
        }
        Button(modifier = Modifier.testTag("button_subtract"), onClick = { clickAction(-1) }) {
            Text(text = "Reducir")
        }
    }
}