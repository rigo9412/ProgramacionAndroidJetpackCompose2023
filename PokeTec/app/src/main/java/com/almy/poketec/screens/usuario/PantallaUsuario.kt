package com.almy.poketec.screens.usuario.PantallaUsuario

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.almy.poketec.data.ListaPokemon
//import com.almy.poketec.data.listaPokemon
import com.almy.poketec.screens.pokedex.Pokemon

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            UsuarioUsuarioTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = Color.Red
//                ) {
//                    Usuario()
//                }
//            }
//        }
//    }
//}

@Composable
fun Usuario(Listaxd: List<Pokemon>) {
    //Variables
    val ListaPokemon=Listaxd
    var vistos by remember { mutableStateOf(90) }
    val totales by remember { mutableStateOf(150) }

    Column(modifier= Modifier
        .fillMaxSize()
        .padding(8.dp),verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier=Modifier.padding(4.dp)) {
            Column(modifier=Modifier.padding(4.dp)) {
                Text(text="Pokemones Vistos: "+ ContarVistos(ListaPokemon).toString())
            }
            Column(modifier=Modifier.padding(4.dp)) {
                Text(text = "Pokemones que faltan: "+ PokeFaltantes(ListaPokemon))
            }
        }
        Row(modifier=Modifier.padding(4.dp)) {
            Text(text = "Pokemones por tipos que faltan")
        }
        Row(modifier=Modifier.padding(4.dp)) {
            Column(modifier=Modifier.padding(4.dp)) {
                Text(text = PokemonesRestantesTipo(ListaPokemon))
            }
        }
        Row(modifier=Modifier.padding(4.dp)) {
            Text(text = "Pokemones legendarios vistos")
        }
        Column() {
            Row(modifier=Modifier.padding(4.dp)){
                Text(text = "Medallas")
            }
            Row(modifier=Modifier.padding(4.dp)) {
                val bitmap = BitmapFactory.decodeStream(LocalContext.current.assets.open("assets/images/medalla1.png"))
                val painter = BitmapPainter(bitmap.asImageBitmap())
                androidx.compose.foundation.Image(
                    painter = painter,
                    contentDescription = "Medalla generacion 1",
                    colorFilter = if (MostrarMedalla(ListaPokemon,1)) null else ColorFilter.tint(Color.Black),
                    modifier = Modifier.size(50.dp)
                )
            }
        }
        
    }
}

//metodo para saber cuantos me faltan conforme los pokemones vistos
fun PokeFaltantes(listaPokemon:List<Pokemon>): Int{
        var faltantes=0
        for (i in listaPokemon) {
            if (!i.discover)
            faltantes+=1
        }
        return faltantes
}

//Todo metodo que saque de los Json los tipo de pokemon y contar si ya estan descubiertos
fun ContarVistos(listaPokemon:List<Pokemon>): Int{
    var descubiertos=0
    for (i in listaPokemon) {
        if (i.discover) {
            descubiertos += 1
        }
    }
    return descubiertos
}

fun PokemonesRestantesTipo(listaPokemon:List<Pokemon>):String{
    //recorremos todo la lista de una para sacar los
    //tipos "unicos", sin repeticiones. Despues con esa lista
    //de elementos "unicos". Volvemos a recorrer, viendo
    //cuantas veces aparecen estos
    val tiposContados: MutableList<String> = mutableListOf()
    var imprimimosTodaLaCasa=""
    for (i in listaPokemon){
        if (!tiposContados.contains(i.type1)){
            tiposContados.add(i.type1)
            print(i.type1+" agregado")
        }
        if (!tiposContados.contains(i.type2)){
            tiposContados.add(i.type2)
            print(i.type2+" agregado")
        }
        println("Pokemon "+i.id+" checado de tipos")
    }

    //con nuestra de tipos "unicos" recorremos la principal y preguntar cuantas veces aparece
    var repetidos=0
    for (i in tiposContados){
        for (x in listaPokemon) {
            if (i==x.type1){
                repetidos+=1
            }
            if (i==x.type2){
                repetidos+=1
            }
        }
        imprimimosTodaLaCasa+="\n"+i+": "+repetidos
        repetidos=0
    }

    return(imprimimosTodaLaCasa)
}

fun MostrarMedalla(listaPokemon:List<Pokemon>,Generacion:Int):Boolean{
    var descubiertos=0
    var totales=0
    for (i in listaPokemon) {
        if(i.generation==Generacion){
            totales+=1
        }

        if (i.discover&&i.generation==Generacion) {
            descubiertos += 1
        }
    }
    return descubiertos==totales

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        Usuario(Listaxd = com.almy.poketec.data.listaPokemon)
}