package com.example.myapplication

//import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.basePink
import com.example.myapplication.ui.theme.nosePink
import com.example.myapplication.ui.theme.nosePinkDark
import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var current by remember { mutableStateOf(0) }
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        /*Text(text = "CURRENT $current", modifier = Modifier.background(Color.LightGray))
                        when(current){
                            "pig" -> HeadPig( onClick = { current = "skull"}, context = null)
                            "skull" -> HeadSkull( onClick = { current = "creeper"})
                            "creeper" -> HeadCreeper( onClick = { current = "pig"})
                        }*/
                        when(current){
                            0 -> HeadPig(context = applicationContext)
                            1 -> HeadSkull(context = applicationContext)
                            2 -> HeadCreeper(context = applicationContext)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Button(
                                enabled = current > 0,
                                onClick = { current -= 1}) {
                                Text(text = "ATRAS")
                            }
                            Button(
                                enabled = current < 2,
                                onClick = { current += 1}) {
                                Text(text = "SIGUIENTE")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        ComponentActivity()
    }
}