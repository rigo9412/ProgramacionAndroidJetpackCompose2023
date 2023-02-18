import kotlin.math.sqrt

fun main() {
    print(calcularOperacionAritmetica("2 + 2 + (2 + 2)"))
}



fun calcularOperacionAritmetica(operacion: String): Double {
    val numeros = mutableListOf<Double>()
    val operadores = mutableListOf<Char>()
    var indice = 0

    while (indice < operacion.length) {
        when {
            operacion[indice].isDigit() -> {
                var numero = 0.0
                while (indice < operacion.length && (operacion[indice].isDigit() || operacion[indice] == '.')) {
                    if (operacion[indice] != '.') {
                        numero = numero * 10 + (operacion[indice] - '0')
                    } else {
                        var decimales = 0.1
                        while (indice < operacion.length - 1 && operacion[indice + 1].isDigit()) {
                            decimales *= 0.1
                            numero += (operacion[indice + 1] - '0') * decimales
                            indice++
                        }
                    }
                    indice++
                }
                numeros.add(numero)
            }
            operacion[indice] == '√' -> {
                numeros.add(sqrt(numeros.removeLast()))
                indice++
            }
            operacion[indice] == '(' -> {
                operadores.add('(')
                indice++
            }
            operacion[indice] == ')' -> {
                while (operadores.last() != '(') {
                    val operador = operadores.removeLast()
                    val num2 = numeros.removeLast()
                    val num1 = numeros.removeLast()
                    numeros.add(aplicarOperacion(operador, num1, num2))
                }
                operadores.removeLast()
                indice++
            }
            "+-*/%".contains(operacion[indice]) -> {
                while (operadores.isNotEmpty() && operadores.last() != '(' && precedenciaOperador(operadores.last()) >= precedenciaOperador(operacion[indice])) {
                    val operador = operadores.removeLast()
                    val num2 = numeros.removeLast()
                    val num1 = numeros.removeLast()
                    numeros.add(aplicarOperacion(operador, num1, num2))
                }
                operadores.add(operacion[indice])
                indice++
            }
            else -> {
                indice++
            }
        }
    }

    while (operadores.isNotEmpty()) {
        val operador = operadores.removeLast()
        val num2 = numeros.removeLast()
        val num1 = numeros.removeLast()
        numeros.add(aplicarOperacion(operador, num1, num2))
    }

    return numeros[0]
}

fun aplicarOperacion(operador: Char, num1: Double, num2: Double): Double {
    return when (operador) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> num1 / num2
        '%' -> num1 % num2
        else -> throw IllegalArgumentException("Operador desconocido: $operador")
    }
}

fun precedenciaOperador(operador: Char): Int {
    return when (operador) {
        '+', '-' -> 1
        '*', '/', '%' -> 2
        '√' -> 3
        else -> throw IllegalArgumentException("Operador desconocido: $operador")
    }
}