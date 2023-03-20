package com.lanazirot.curpgenerator.screens

sealed class Routes {
    abstract val route: String

    object CURP : Routes() {
        override val route = "curp"
    }

    object LOADING : Routes() {
        override val route = "loading"
    }

    data class RESULT(val curp: String = "", val name: String = "") : Routes() {
        override val route = "result/{curp}/{name}"
        fun route() = "result/$curp/$name"
    }
}