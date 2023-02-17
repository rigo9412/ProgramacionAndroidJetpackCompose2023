// 10. Elaborar una función que reciba una operación aritmética en formato texto y que la
// resuelva, la operación solicitada son suma, resta, división, producto, residuo y
// porcentaje.
// a. Agregar funcionalidad de jerarquía de operadores
// b. Agregar funcionalidad de raíz cuadrada.

import java.util.Stack
import kotlin.math.*

fun main(){
    var result = calcularOperacion("( 3 + 2 ) / 2 + 5 * 10 R 2")
    print(result)
}

fun calcularOperacion(operacion: String): Double {
    val elementos = operacion.split(" ")
    val pilaNumeros = Stack<Double>()
    val pilaOperadores = Stack<String>()
    var i = 0

    while (i < elementos.size) {
        val elemento = elementos[i]

        if (elemento == "(") {
            pilaOperadores.push(elemento)
        } else if (elemento == ")") {
            while (pilaOperadores.peek() != "(") {
                val operador = pilaOperadores.pop()
                val segundoNumero = pilaNumeros.pop()
                val primerNumero = pilaNumeros.pop()
                val resultado = realizarOperacion(primerNumero, segundoNumero, operador)
                pilaNumeros.push(resultado)
            }
            pilaOperadores.pop()
        } else if (esOperador(elemento)) {
            while (!pilaOperadores.empty() && tieneMayorPrioridad(elemento, pilaOperadores.peek())) {
                val operador = pilaOperadores.pop()
                val segundoNumero = pilaNumeros.pop()
                val primerNumero = pilaNumeros.pop()
                val resultado = realizarOperacion(primerNumero, segundoNumero, operador)
                pilaNumeros.push(resultado)
            }
            pilaOperadores.push(elemento)
        } else {
            pilaNumeros.push(elemento.toDouble())
        }
        i++
    }

    while (!pilaOperadores.empty()) {
        val operador = pilaOperadores.pop()
        val segundoNumero = pilaNumeros.pop()
        val primerNumero = pilaNumeros.pop()
        val resultado = realizarOperacion(primerNumero, segundoNumero, operador)
        pilaNumeros.push(resultado)
    }

    return pilaNumeros.pop()
}

fun esOperador(elemento: String): Boolean {
    return elemento == "+" || elemento == "-" || elemento == "*" || elemento == "/" || elemento == "%" || elemento == "R" || elemento == "!"
}

fun tieneMayorPrioridad(op1: String, op2: String): Boolean {
    if (op2 == "(" || op2 == ")") {
        return false
    }
    if ((op1 == "*" || op1 == "/" || op1 == "%" || op1 == "R" || op1 == "!" ) && (op2 == "+" || op2 == "-")) {
        return false
    }
    return true
}

fun realizarOperacion(primerNumero: Double, segundoNumero: Double, operador: String): Double {
    when (operador) {
        "+" -> return primerNumero + segundoNumero
        "-" -> return primerNumero - segundoNumero
        "*" -> return primerNumero * segundoNumero
        "/" -> return primerNumero / segundoNumero
        "R" -> return primerNumero % segundoNumero
        "%" -> return primerNumero * segundoNumero / 100
        "!" -> return Math.sqrt(segundoNumero)
    }
    return 0.0
}
