//Elaborar una función que reciba una operación aritmética en formato texto y que la resuelva,
// la operación solicitada son suma, resta, división, producto, residuo y porcentaje.
import kotlin.math.sqrt



fun main(){
    println(operacion('*',2.0,4.0))
}

fun operacion(operador: Char, num1: Double, num2: Double = 0.0): Double {
    return when (operador) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> num1 / num2
        'R' -> num1 % num2
        '%' -> num1 * (num2/100)
        'S' -> Math.sqrt(num1)
        else -> throw IllegalArgumentException("Error: Operador no válido")
    }
}

fun resolverOperacion(operacion: String): Double {
    val listanumeros = mutableListOf<Double>()
    val listaoperadores = mutableListOf<Char>()
    var indice = 0
    while (indice < operacion.length) {
        when {
            operacion[indice].isDigit() -> {
                var numStr = ""
                while (indice < operacion.length && (operacion[indice].isDigit() || operacion[indice] == '.')) {
                    numStr += operacion[indice++]
                }
                listanumeros.add(numStr.toDouble())
            }
            operacion[indice] == '+' || operacion[indice] == '-' || operacion[indice] == '*' || operacion[indice] == '/'|| operacion[indice] == 'R' || operacion[indice] == '%' || operacion[indice] == 'S' -> {
                listaoperadores.add(operacion[indice++])
            }
            operacion[indice] == '(' -> {
                listaoperadores.add(operacion[indice++])
            }
            operacion[indice] == ')' -> {
                while (listaoperadores.last() != '(') {
                    if (listaoperadores.last() == 'S'){
                        val operador = listaoperadores.removeLast()
                        val x = listanumeros.removeLast()
                        listanumeros.add(operacion(operador, x))
                    } else {
                        val operador = listaoperadores.removeLast()
                        val y = listanumeros.removeLast()
                        val x = listanumeros.removeLast()
                        listanumeros.add(operacion(operador, x, y))
                    }
                }
                listaoperadores.removeLast()
                indice++
            }
            operacion[indice] == ' ' -> {
                indice++
            }
            else -> {
                throw IllegalArgumentException("Error: Caracter no válido")
            }
        }
    }
    while (listaoperadores.isNotEmpty()) {
        if (listaoperadores.last() == 'S'){
            val operador = listaoperadores.removeLast()
            val x = listanumeros.removeLast()
            listanumeros.add(operacion(operador, x))
        } else{
            val operador = listaoperadores.removeLast()
            val y = listanumeros.removeLast()
            val x = listanumeros.removeLast()
            listanumeros.add(operacion(operador, x, y))
        }
    }
    return listanumeros[0]
}