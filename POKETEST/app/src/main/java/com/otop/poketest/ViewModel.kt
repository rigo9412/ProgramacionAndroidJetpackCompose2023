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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.InputStream

class MainViewModel : ViewModel() {
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
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



    fun navigateToHome() {
        if ( viewList.value.size == list.size){
            _currentScreen.value = Screen.Complete
        }
        _currentScreen.value = Screen.Home
    }

    fun navigateToPokedex() {
        _currentScreen.value = Screen.Pokedex
    }

    fun navigateToProfile() {
        _currentScreen.value = Screen.Profile
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

    fun getImagePath(id:Int): String {
        return "pokemons/${id.toString().padStart(3,'0')}.png"
    }
}

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Pokedex: Screen("pokedex")
    object Profile: Screen("profile")
    object Failed: Screen("failed")
    object Complete: Screen("complete")
}