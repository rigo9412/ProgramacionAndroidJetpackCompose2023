////EJERCICIO 3
////Elaborar una función que reciba un texto y cuente el numero de letras que tiene
import java.text.Normalizer

fun main() {
    println(calcularNumeroLetras("aaaaabbbbcccccccdddddd Hola me Llamo AlaéáéíóůÃÇÕÊeÚclairn".unaccent().uppercase()))
}

fun String.unaccent(): String {
    return "\\p{InCombiningDiacriticalMarks}+".toRegex().replace(Normalizer.normalize(this, Normalizer.Form.NFD), "")
}

fun calcularNumeroLetras(cad: String):MutableMap<String, Int> {
    var map :MutableMap<String, Int> = mutableMapOf()
    var cont = 0
    var tamanio = cad.length
    
    while(cont < tamanio) {
        var lett = cad[cont].toString()
        if(map.containsKey(lett)) {
            map[lett] = map.getValue(lett) + 1
        } else {
            map.put(lett, 1)
        }

        cont++
    }
        
    return map
}