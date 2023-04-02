package com.almy.poketec.screens.usuario.PantallaUsuario

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.almy.poketec.R
import com.almy.poketec.screens.pokedex.Pokemon
import com.almy.poketec.ui.theme.naranja
import com.game.guesspoke.screens.game.listaPokedex
import com.game.guesspoke.screens.game.listaPuntuaciones



//lista que se usa de preba para las puntuaciones
val puntuaciones: List<Int> = listOf(80,70,30)
@Composable
fun Usuario(Listaxd: List<Pokemon>,
            modifier: Modifier = Modifier,
            elevation: Dp = 0.dp,
            border: BorderStroke? = BorderStroke(0.dp, Color.Transparent),
            shape: Shape = MaterialTheme.shapes.medium) {

    Card(
        shape = shape,
        elevation = elevation,
        border = border,
        modifier = modifier

    ){
        val darkMode = remember{ mutableStateOf(false) }

        Column(
            Modifier
                .background(Color(0xD788B6DB))) {

            Row {
                Titulo(
                    elevation = 0.dp,
                    border = BorderStroke(0.dp, Color.Transparent),
                    darkMode = darkMode
                )
            }
            Row {
                InformacionDelUsuario(Listaxd = com.almy.poketec.data.listaPokemon,darkMode = darkMode)

            }

        }
    }

}

fun obtenerTresMayores(lista: List<Int>): List<Int> {
    val listaOrdenada = lista.sortedDescending()
   return listaOrdenada.take(3)  }


//metodo para saber cuantos me faltan conforme los pokemones vistos
fun PokeFaltantes(listaPokemon:List<Pokemon>): Int{
        var faltantes=0
        for (i in listaPokemon) {
            if (!i.discover){
                faltantes+=1
            }

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
        if (!tiposContados.contains(i.type1)&&i.type1!=""){
            tiposContados.add(i.type1)
            print(i.type1+" agregado")
        }
        if (!tiposContados.contains(i.type2)&&i.type2!=""){
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

        for (x in listaPokedex) {
            if (i==x.type1){
                repetidos-=1
            }
            if (i==x.type2){
                repetidos-=1
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

fun ContarPokemonesLegendarios(listaPokemon:List<Pokemon>):Int{
    var descubiertos=0
    for (i in listaPokedex) {
        if (i.discover && i.legendary=="true" || i.legendary=="True") {
            descubiertos += 1
        }
    }
    return descubiertos
}






//metodos para la vista


@OptIn(ExperimentalTextApi::class)
@Composable
fun Titulo(
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    border: BorderStroke? = null,
    background: Color = Color.Transparent,
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,
    darkMode: MutableState<Boolean>
) {
    val gradient = Brush.linearGradient(
        0.0f to  Color(0xFFA770EF),
        500.0f to Color(0xFFFDB99B),
        start= Offset.Zero,
        end= Offset.Infinite
    )
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )


    val fontName = GoogleFont("Play")


    val fontFamily = FontFamily(
        Font(googleFont = fontName, fontProvider = provider,
            weight = FontWeight.Bold)
    )
    Card(
        backgroundColor = background,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation,
        border = border,
        modifier = modifier
            .padding(15.dp)
            .background(color = Color.Transparent)
    ) {


        // Contenedor
        Column (modifier = Modifier
            .background(color = Color.Transparent)
            .padding(20.dp)) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .background(color =
                        if(darkMode.value) Color(0xFF0032c50)
                        else Color(0xFF3aa0f9), shape = CircleShape)
                        .border(BorderStroke(2.dp, naranja), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    MostrarImagenPrincipal(ruta = "usuario.png")
                }
                Spacer(modifier = Modifier.width(32.dp))

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)) {
                    // Encabezado
                    Text(text = "Estadisticas del usuario", fontSize = 30.sp, fontFamily = fontFamily,color = Color(
                        0xFFFFFFFF
                    )
                    )

//                    // Subtítulo
//                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                        Text(text = "Estadisticas del usuario", style = MaterialTheme.typography.body1)
//                    }
                }
            }
        }
    }
}
@Composable
fun getAssetBitmap(context: Context, assetPath: String): Bitmap {
    return BitmapFactory.decodeStream(context.assets.open(assetPath))
}

@Composable
fun MostrarImagenPrincipal(ruta: String) {
    val bitmap = getAssetBitmap(LocalContext.current, ruta)
    val painter = BitmapPainter(bitmap.asImageBitmap())
    Image(
        painter = painter,
        contentDescription = "pokemon",
        modifier = Modifier
            .background(Color.Transparent)
            .height(100.dp)
            .width(100.dp),
    )
}

@Composable
fun TipoPokemon(datos:String) {
    var expanded by remember { mutableStateOf(false) }

    val gradient = Brush.linearGradient(
        0.0f to  Color(0xFF86adcc ),
        500.0f to Color(0xFFD4E4EF),
    )
    Card(modifier = Modifier
        .padding(20.dp)
        .background(gradient)) {
        Column(modifier = Modifier
            .animateContentSize()
            .background(gradient)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clickable { expanded = !expanded }
            ) {
                Column() {
//                    Text(text = expandableCardItem.title, style = MaterialTheme.typography.h6)
                    Text(
                        text = "Pokemon por tipos que faltan",
                        style = MaterialTheme.typography.body1
                    )
                }

//                ExpandableCardIcon(
//                    expanded = expanded,

//                    modifier = Modifier.align(
//                        Alignment.CenterEnd
//                    )
//                )
            }

            if (expanded)
                Divider(thickness = Dp.Hairline, modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(gradient))

            Text(
                text = "${datos}",
                modifier = Modifier
                    .height(if (expanded) 500.dp else 0.dp)
                    .padding(5.dp), textAlign = TextAlign.Center,color=Color.Black
            )
        }
    }
}

@Composable
fun NumeroPokemon(
    texto: String,
    colo1:Color,
    color2:Color,
    cantidadMostrada:String,
){

    val colorFondo = Brush.linearGradient(
        0.0f to  colo1,
        500.0f to color2,
        start= Offset.Zero,
        end= Offset.Infinite
    )

    Column(
        modifier = Modifier
            .border(BorderStroke(0.dp, Color.Transparent))
            .fillMaxWidth()) {
        Card(elevation = 15.dp, modifier = Modifier
            .background(Color.Transparent)
            .padding(15.dp)){
            Column(modifier = Modifier
                .background(colorFondo)
                .height(150.dp)
                .width(300.dp)
                .clickable { }){
                    Box(modifier = Modifier.padding(10.dp)){
                        Text(texto, fontSize = 17.sp)

                    }
                    Box(modifier = Modifier.padding(start = 120.dp),contentAlignment = Alignment.BottomEnd){
                        Text(cantidadMostrada.toString(), fontSize = 70.sp, textAlign = TextAlign.Right,color = Color.White) }
                }
            }
        }
    }

@Composable
fun Medalla(
    ListaPokemon: List<Pokemon>,
    colo1:Color,
    color2:Color,
){

    val colorFondo = Brush.linearGradient(
        0.0f to  colo1,
        500.0f to color2,
        start= Offset.Zero,
        end= Offset.Infinite
    )

    Column(
        modifier = Modifier
            .border(BorderStroke(0.dp, Color.Transparent))
            .fillMaxWidth()) {
        Card(elevation = 15.dp, modifier = Modifier
            .background(Color.Transparent)
            .padding(15.dp)){
            Column(modifier = Modifier
                .background(colorFondo)
                .height(150.dp)
                .width(300.dp)
                .clickable { }){
                Row(modifier=Modifier.padding(4.dp)){
                Text(text = "Medallas")
            }
            Row(modifier=Modifier.padding(4.dp)) {
                val bitmap = BitmapFactory.decodeStream(LocalContext.current.assets.open("images/medalla1.png"))
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
}




@Composable
fun TablaPuntajes(listaPuntajes:List<Int>){
    LazyRow{
        items(listaPuntajes){ puntajeTop -> PuntajeItemCard(Puntaje =puntajeTop)
        }
    }
}

@Composable
fun PuntajeItemCard(Puntaje:Int){
    val colorItemCard = Brush.linearGradient(
        0.0f to  Color(0xFF7B78F5),
        500.0f to Color(0xFFF79FB4),
        start= Offset.Zero,
        end= Offset.Infinite
    )

    Card(
        Modifier
            .padding(8.dp)
            .width(200.dp)
            .height(150.dp)) {
        ConstraintLayout(
            Modifier
                .padding(8.dp)
                .background(colorItemCard)) {
            val (puntaje,spacer,minuto,contadorTop ) = createRefs()
            createVerticalChain(puntaje,minuto, chainStyle = ChainStyle.SpreadInside)
            Text(text = "Puntaje obtenido:${Puntaje}",
                Modifier
                    .padding(10.dp)
                    .constrainAs(puntaje) {
                        start.linkTo(contadorTop.end)
                    },
                fontSize = 20.sp)

            Spacer(modifier = Modifier
                .width(10.dp)
                .constrainAs(spacer) {
                    start.linkTo(puntaje.end)
                })

        }
    }


}

//es el segundo card donde se guarda la informacion del usuario
@Composable
fun InformacionDelUsuario(
    Listaxd: List<Pokemon>,
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    border: BorderStroke? = BorderStroke(3.dp, Color.White),
    background: Color = Color(0xE8FFFFFF),
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,
    darkMode: MutableState<Boolean>
){
    val ListaPokemon=Listaxd
    Card(
        backgroundColor = if(darkMode.value) Color(0xFF000000) else Color(0xE8FFFFFF),
        contentColor = contentColor,
        shape = shape,
        elevation = elevation,
        border = border,
        modifier = modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
    ){
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(30.dp)) {

            Row {
//                Muestra los tres primero puntajes
               TablaPuntajes(obtenerTresMayores(listaPuntuaciones))
            }

            Row {
//                Se divide entre los pokemones vistos y lo no vistos
                NumeroPokemon(texto = "Pokemons vistos:", colo1 = Color(0xFFDA2938), color2 = Color(0xFFFFAE3F), cantidadMostrada = ContarVistos(ListaPokemon).toString())

            }
            Row {
//                Se divide entre los pokemones vistos y lo no vistos
                NumeroPokemon(texto = "Pokemons que faltan:", colo1 = Color(0xFF7B78F5), color2 = Color(0xFFF79FB4), cantidadMostrada = PokeFaltantes(ListaPokemon).toString())

            }
            Row{
                TipoPokemon(datos = PokemonesRestantesTipo(ListaPokemon))
            }

            Row{
                //espacio para agregar los pokemones legendarios
                NumeroPokemon(texto = "Legendarios Vistos:", colo1 = Color(0xFFa7a6cb), color2 = Color(0xFF8989ba), cantidadMostrada = ContarPokemonesLegendarios(ListaPokemon).toString())

            }
            Row{
                Medalla(ListaPokemon = ListaPokemon, colo1 = Color(0xFFe4efe9), color2 =Color(0xFF93a5cf) )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        Usuario(Listaxd = com.almy.poketec.data.listaPokemon)
}