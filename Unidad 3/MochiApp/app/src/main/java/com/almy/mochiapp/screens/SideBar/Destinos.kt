package com.myriam.pantallalateral

import com.almy.mochiapp.R

sealed class Destinos(
    val icon : Int,
    val title :String,
    val ruta: String
){

    object Usuario: Destinos(R.drawable.persona,"Usuario","usuario")
    object TareasCompletadas: Destinos(R.drawable.tareas,"tareas completadas","tareascompletadas")
    object CrearNota: Destinos(R.drawable.crarnota,"crear notas ","crearnotas")
    object ListaTareas: Destinos(R.drawable.listatareas,"lista tareas","listatareas")
    object Login: Destinos(R.drawable.close,"cerrar sesion","login")
}
//.padding(vertical = 8.dp).clickable { onCloseDrawer()}