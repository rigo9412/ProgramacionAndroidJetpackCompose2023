//10. Elaborar una función que reciba una operación aritmética en formato texto y que la
//resuelva, la operación solicitada son suma, resta, división, producto, residuo y
//porcentaje.
//a. Agregar funcionalidad de jerarquía de operadores
//b. Agregar funcionalidad de raíz cuadrada
import kotlin.math.sqrt

fun main(){
    println("Ingrese las operaciones, los signos son: ")
    println("Suma = +, Resta = -, Producto = *, Division = /, Residuo = R -> (num R)")
    println("Porcentaje = % -> (numero | porcentaje)")
    println("Raiz = S -> (numero S) | (S numero)")
    println("--------------------------------------------------------------")
    print("Operacion: ")
    val resultado = readln()
    println(calcularOperacion(resultado))
/*Ejemplos
    "((5*5)+(9*10))/2"
    "100 % 10"
    "5R2"
    "S81" ó "81S"
*/
}

fun calcularOperacion(operacion: String): Double {
    val numeros = mutableListOf<Double>()
    val operadores = mutableListOf<Char>()
    var indice = 0

    while (indice < operacion.length) {
        when {
            operacion[indice].isDigit() ->
            {
                var numStr = ""
                while (indice < operacion.length && (operacion[indice].isDigit() || operacion[indice] == '.')) {
                    numStr += operacion[indice++]
                }
                numeros.add(numStr.toDouble())
            }

            operacion[indice] == '+' || operacion[indice] == '-' || operacion[indice] == '*' || operacion[indice] == '/' || operacion[indice] == '%' || operacion[indice] == 'S' || operacion[indice] == 'R'-> {
                operadores.add(operacion[indice++])
            }

            operacion[indice] == '(' -> {
                operadores.add(operacion[indice++])
            }
            operacion[indice] == ')' -> {
                while (operadores.last() != '(') {
                    if (operadores.last() == 'S'){
                        val operador = operadores.removeLast()
                        val num1 = numeros.removeLast()
                        numeros.add(realizarOperacion(operador, num1))
                    } else {
                        val operador = operadores.removeLast()
                        val num2 = numeros.removeLast()
                        val num1 = numeros.removeLast()
                        numeros.add(realizarOperacion(operador, num1, num2))
                    }
                }
                operadores.removeLast()
                indice++
            }
            operacion[indice] == ' ' -> {
                indice++
            }
            else -> {
                throw IllegalArgumentException("Caracter no valido")
            }
        }
    }

    while (operadores.isNotEmpty()) {
        if (operadores.last() == 'S'){
            val operador = operadores.removeLast()
            val num1 = numeros.removeLast()
            numeros.add(realizarOperacion(operador, num1))
        } else {
            val operador = operadores.removeLast()
            val num2 = numeros.removeLast()
            val num1 = numeros.removeLast()
            numeros.add(realizarOperacion(operador, num1, num2))
        }
    }

    return numeros[0]
}

fun realizarOperacion(operador: Char, num1: Double, num2: Double = 0.0): Double {
    return when (operador) {
        //suma, resta, producto, division, residuo, porcentaje, raiz.
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> num1 / num2
        'R' -> num1 % num2
        '%' -> num1 * (num2/100)
        'S' -> Math.sqrt(num1)
        else -> throw IllegalArgumentException("Operador no valido")
    }
}