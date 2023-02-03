////EJERCICIO 3
////Elaborar una función que reciba un texto y cuente el numero de letras que tiene
fun main() {
    var cadee =  "abc234567876543234567876.5/.45?>%&$%^&#@$^#$%@43"
    println(calcularNumeroLetras(cadee))
}

fun calcularNumeroLetras(cad: String):Int {
    return cad.replace(("[^A-Za-z]").toRegex(), "").length
}
