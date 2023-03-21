package com.example.formulario.ui.theme

sealed class rutas (val route: String){
    object Form: rutas("form")
    object Result: rutas("result?curp={curp}&name={name}&lastname={lastname}")
        fun genarateRoutes(curp: String, name: String):String{
            return "result?curp=${curp}&name=${name}&lastname=${name}"
        }
}