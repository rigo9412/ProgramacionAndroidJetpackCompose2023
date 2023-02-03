// Elaborar una función que resiba un texto y cuente cuantas veces aparece una letra en el texto (solo letras)
//Nota: La letra con sus variantes es la misma letra


import java.text.Normalizer
fun main(){
    println(contarLetras("Lorém ipsum dolor sit amet augéndas civitas efficiántur firmitatem fonté indicavérunt ipse iracundiae iustius magis oderis sitne stare ultrices viderer."))
}

fun contarLetras(palabra: String): Map<Char, Int> {
    var string = palabra
    string = string.lowercase()
    string = Normalizer.normalize(string, Normalizer.Form.NFD)
    var map = mutableMapOf<Char,Int>()
    var x = 0
    while(x < string.length)
    {
        if(string[x].isLetter())
        {
            map.merge(string[x],1) 
            {
                old, value -> old+value
            }
        }
        x += 1
    }
    return map;
}