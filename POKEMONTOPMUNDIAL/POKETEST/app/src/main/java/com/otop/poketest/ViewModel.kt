package com.otop.poketest

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import coil.compose.rememberAsyncImagePainter
import com.otop.poketest.data.PokemonEntity
import com.otop.poketest.data.models.Trainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.InputStream

class MainViewModel : ViewModel() {
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Complete)
    val currentScreen: StateFlow<Screen> = _currentScreen

    var darkMode = mutableStateOf(false)
    var showDialog = mutableStateOf(false)

    var timer = mutableStateOf(10)

    var intentos = mutableStateOf(0)
    var gaming =  mutableStateOf(false)

    var score = mutableStateOf(0)
    var spree = mutableStateOf(0)

    var biggestSpree = mutableStateOf(0)
    var biggestScore = mutableStateOf(0)

    var startCount = mutableStateOf(false)

    lateinit var list: List<PokemonEntity>

    lateinit var currentPokemon: PokemonEntity
    lateinit var assetMannager : AssetManager

    var viewList: MutableState<List<PokemonEntity>> = mutableStateOf(mutableListOf())

    var vidas: MutableState<List<Int>> = mutableStateOf(listOf(1, 1, 1))

    var top10: MutableState<Array<Trainer>> = mutableStateOf(arrayOf(
        Trainer("Trainer1", "País1", 1.5, 3),
        Trainer("Trainer2", "País2", 2.0, 2),
        Trainer("Trainer3", "País3", 0.5, 5),
        Trainer("Trainer4", "País4", 2.0, 2),
        Trainer("Trainer5", "País5", 0.5, 5)
    ))

    var countryTop = mutableStateOf("")

    var topTrainer = Trainer("","",0.0,0)
    fun createTrainer(nombre:String,tiempo:Double){
        topTrainer.nombre = nombre
        topTrainer.pais = countryTop.value
        topTrainer.tiempo = tiempo
        topTrainer.intentos = intentos.value
    }

    fun navigateToHome() {
        _currentScreen.value = Screen.Home
    }

    fun navigateToPokedex() {
        _currentScreen.value = Screen.Pokedex
    }

    fun navigateToProfile() {
        _currentScreen.value = Screen.Profile
    }
    fun navigateToTop() {
        _currentScreen.value = Screen.Top
    }
    fun navigateToFailed() {
        _currentScreen.value = Screen.Failed
    }



    fun getRandomPokemon(isFake: Boolean): PokemonEntity {
        if (isFake){
            return list.random()
        }
        else{
            currentPokemon = list.random()
            while (viewList.value.contains(currentPokemon)){
                currentPokemon = list.random()
            }
            return currentPokemon
        }
    }
    fun Correct(){
        val currentList = viewList.value.toMutableList()
        currentList.add(currentPokemon)
        viewList.value = currentList
        spree.value++
        score.value++
        timer.value = 10
        if (viewList.value.count() == 151)
        {
            juegoAcabado()
        }
    }
    var i = 0
    fun vidaMenos(){
        showDialog.value = true
        val nuevasVidas = vidas.value.toMutableList()
        nuevasVidas[i] = 0
        vidas.value = nuevasVidas
        biggestSpree.value = spree.value
        spree.value = 0
        timer.value = 10
        i++
        if ( i == 3 ){
            _currentScreen.value = Screen.Failed
            intentos.value++
            vidas = mutableStateOf(listOf(1, 1, 1))
            gaming.value = false
            i = 0
            biggestScore.value = score.value
            score.value = 0
        }
    }

    fun juegoAcabado(){
        _currentScreen.value = Screen.Complete
        intentos.value++
        vidas = mutableStateOf(listOf(1, 1, 1))
        gaming.value = false
        i = 0
        biggestScore.value = score.value
        score.value = 0
    }

    fun getImagePath(id:Int): String {
        return "pokemons/${id.toString().padStart(3,'0')}.png"
    }
}

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Top: Screen("top")
    object Pokedex: Screen("pokedex")
    object Profile: Screen("profile")
    object Failed: Screen("failed")
    object Complete: Screen("complete")
}