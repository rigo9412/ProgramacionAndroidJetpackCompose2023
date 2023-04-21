package com.example.simondiceviewmodel.screeens

sealed class Screens {
    object Init : Screens()
    class Puntaciones(val listaPuntaciones: MutableList<Top10State>) : Screens()
}