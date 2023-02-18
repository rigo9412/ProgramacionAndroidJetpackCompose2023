// 10.Elaborar una función que reciba una operación aritmética en formato texto y que la resuelva,
// la operación solicitada son suma, resta, división, producto, residuo y porcentaje.
// a.Agregar funcionalidad de jerarquía de operandos
// b.Agregar funcionalidad de raíz cuadrada
import kotlin.math.sqrt

fun main(){
    println(ejecutaroperador("10*2"))
}

fun ejecutaroperador(operador: String): Double {
    val numeros = mutableListOf<Double>()
    val operandos = mutableListOf<Char>()
    var i = 0

    while (i < operador.length) {
        when {operador[i].isDigit() -> {
                var numStr = ""
                while (i < operador.length && (operador[i].isDigit() || operador[i] == '.')) { numStr += operador[i++] }
                numeros.add(numStr.toDouble())
            }
                    operador[i] == '+' || 
                    operador[i] == '-' ||
                    operador[i] == 'R' ||
                    operador[i] == '%' ||
                    operador[i] == '*' ||
                    operador[i] == '/' ||
                    operador[i] == 'S' -> { operandos.add(operador[i++]) } operador[i] == '(' -> { operandos.add(operador[i++])
            }
            operador[i] == ')' -> {
                while (operandos.last() != '(') {
                    if (operandos.last() == 'S'){
                        val operador = operandos.removeLast()
                        val numero1 = numeros.removeLast()
                        numeros.add(verificarOperador(operador, numero1))
                    } else {
                        val operador = operandos.removeLast()
                        val numero2 = numeros.removeLast()
                        val numero1 = numeros.removeLast()
                        numeros.add(verificarOperador(operador, numero1, numero2))
                    }
                }
                operandos.removeLast();i++
            }
            operador[i] == ' ' -> { i++}
            else -> { throw IllegalArgumentException("Error") }
        }
    }

    while (operandos.isNotEmpty()) {
        if (operandos.last() == 'S'){
            val operador = operandos.removeLast()
            val numero1 = numeros.removeLast()
            numeros.add(verificarOperador(operador, numero1))
        } else{
            val operador = operandos.removeLast()
            val numero2 = numeros.removeLast()
            val numero1 = numeros.removeLast()
            numeros.add(verificarOperador(operador, numero1, numero2))
        }
    }
    return numeros[0]
}

fun verificarOperador(operador: Char, numero1: Double, numero2: Double = 0.0): Double {
    return when (operador) {
        '+' -> numero1 + numero2; '*' -> numero1 * numero2
        '/' -> numero1 / numero2; '-' -> numero1 - numero2
        'R' -> numero1 % numero2; 'S' -> Math.sqrt(numero1)
        '%' -> numero1 * (numero2/100)
        else -> throw IllegalArgumentException("Error")
    }
}