//Elaborar una función que reciba un texto y cuente cuantas veces aparece una letra en el texto (solo letras)
//ejemplo a=5, o=40.
import java.text.Normalizer
import java.util.TreeMap

fun main(){
    println(ContarLetras("Lorém ipsum dolor sit amet augéndas civitas efficiántur firmitatem"))
}

fun ContarLetras(texto : String) : String{
    var cadena = texto
    cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD)
    cadena = cadena.lowercase()

    //var mapa = mutableMapOf<Char,Int>()
    var mapa = TreeMap<Char, Int>()
    var i = 0
    while(i < cadena.length){
        if(cadena[i].isLetter()){
            mapa.merge(cadena[i],1) {
                old, value -> old+value
            }
        }
        i += 1
    }
    var mensaje = mapa.toString()
    return mensaje.substring(1,mensaje.length-1)
}