package com.example.simondiceviewmodel.screeens
import kotlin.random.Random

class Juego() {
    val incrementoNivel = 1
    val incrementoRecord =10
    var final = false
    var pasosMax =1
    var record = 0
    var nivel =1
    var currentIndex=-1
    var currentJugadorIndex=0
    var listaAcciones= mutableListOf<Accion>()


    val inicio get() = listaAcciones.isNotEmpty()
    val fin get() = final
    val endSpeak get() = listaAcciones.isNotEmpty() && listaAcciones.lastIndex < currentIndex
    val curIndex get() = currentIndex
    val curJugadorIndex get() = currentJugadorIndex
    val lastActionIndex get() = listaAcciones.lastIndex



    fun obtenerCurrentAction(): Accion{
        return if(listaAcciones.isNotEmpty() && currentIndex >=0 && currentIndex<= listaAcciones.lastIndex)
            listaAcciones[currentIndex] else Accion.sinAccion
    }

    fun siguienteAccion(): Accion{
        if(listaAcciones.isEmpty() ||currentIndex > listaAcciones.lastIndex) return Accion.sinAccion
        if (currentIndex == listaAcciones.lastIndex){
            currentIndex++
            return Accion.sinAccion
        }
        currentIndex++
        return  listaAcciones[currentIndex]
    }

    fun empezar(): Juego{
        record=0
        nivel=1
        final=false
        currentJugadorIndex=0
        currentIndex=0
        generarAcciones()
        return this
    }

    private fun generarAcciones() {
        listaAcciones.clear()
        for (i in 1.. pasosMax){
            listaAcciones.add(Accion.values()[Random.nextInt(1, Accion.values().count())])
        }
    }

    fun terminar(nomjugador: String): Jugador{
        val jugador =Jugador(nomjugador,record,nivel)
        record=0
        nivel=0
        pasosMax=1
        currentIndex=-1
        currentJugadorIndex= -1
        listaAcciones.clear()
        return jugador
    }

    fun validateAction(action: Accion): Boolean{
        if(listaAcciones[currentJugadorIndex] != action) return  false

        if(listaAcciones.lastIndex == currentJugadorIndex){
            levelUp()
        }else{
            currentJugadorIndex++
        }

        return true
    }

    private fun levelUp(){
        pasosMax += 1
        nivel++
        record += (incrementoRecord * nivel)
        currentIndex = -1
        currentJugadorIndex = 0
        generarAcciones()
    }
}