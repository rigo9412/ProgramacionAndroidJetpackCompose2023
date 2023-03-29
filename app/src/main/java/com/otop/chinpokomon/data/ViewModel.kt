package com.otop.chinpokomon.data
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    val greeting = mutableStateOf("Hola!")

    fun setGreeting(newGreeting: String) {
        greeting.value = newGreeting
    }

    // Si necesitas algún proceso asíncrono, también puedes declararlo aquí
    // por ejemplo, una llamada a una API
}
