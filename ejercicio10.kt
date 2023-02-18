/*Elaborar una función que reciba una operación aritmética en formato texto y que la resuelva, la operación solicitada son:
suma, resta, división, producto, residuo y porcentaje.
Agregar funcionalidad de jerarquía de operadores
Agregar funcionalidad de raíz cuadrada.
 */
import kotlin.math.sqrt

fun main(){
    println(resolverOperacion("((5*5)+(S25))/2"))
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
            operacion[indice] == '+' || operacion[indice] == '-' || operacion[indice] == '*' || operacion[indice] == '/' || operacion[indice] == '%' || operacion[indice] == 'S' -> {
                listaoperadores.add(operacion[indice++])
            }
            operacion[indice] == '(' -> {
                listaoperadores.add(operacion[indice++])
            }
            operacion[indice] == ')' -> {
                while (listaoperadores.last() != '(') {
                    if (listaoperadores.last() == 'S'){
                        val operador = listaoperadores.removeLast()
                        val num1 = listanumeros.removeLast()
                        listanumeros.add(calcularOperacion(operador, num1))
                    } else {
                        val operador = listaoperadores.removeLast()
                        val num2 = listanumeros.removeLast()
                        val num1 = listanumeros.removeLast()
                        listanumeros.add(calcularOperacion(operador, num1, num2))
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
            val num1 = listanumeros.removeLast()
            listanumeros.add(calcularOperacion(operador, num1))
        } else{
            val operador = listaoperadores.removeLast()
            val num2 = listanumeros.removeLast()
            val num1 = listanumeros.removeLast()
            listanumeros.add(calcularOperacion(operador, num1, num2))
        }
    }

    return listanumeros[0]
}

fun calcularOperacion(operador: Char, num1: Double, num2: Double = 0.0): Double {
    return when (operador) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> num1 / num2
        '%' -> num1 % num2
        'S' -> Math.sqrt(num1)
        else -> throw IllegalArgumentException("Error: Operador no válido")
    }
}