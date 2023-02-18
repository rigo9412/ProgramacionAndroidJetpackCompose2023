//10. Elaborar una función que reciba una operación aritmética en formato texto y que la
//resuelva, la operación solicitada son suma, resta, división, producto, residuo y
//porcentaje.
//a. Agregar funcionalidad de jerarquía de operadores
//b. Agregar funcionalidad de raíz cuadrada

fun main(){
    val resultado = calcularOperacion("((5*5)+(9*10))/2")
    println(resultado)
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

            operacion[indice] == '+' || operacion[indice] == '-' || operacion[indice] == '*' || operacion[indice] == '/' || operacion[indice] == '%' -> {
                operadores.add(operacion[indice++])
            }

            operacion[indice] == '(' -> {
                operadores.add(operacion[indice++])
            }
            operacion[indice] == ')' -> {
                while (operadores.last() != '(') {
                    val operador = operadores.removeLast()
                    val num2 = numeros.removeLast()
                    val num1 = numeros.removeLast()
                    numeros.add(realizarOperacion(operador, num1, num2))
                }
                operadores.removeLast()
                indice++
            }
            operacion[indice] == ' ' -> {
                indice++
            }
            else -> {
                throw IllegalArgumentException("Caracter no válido")
            }
        }
    }

    while (operadores.isNotEmpty()) {
        val operador = operadores.removeLast()
        val num2 = numeros.removeLast()
        val num1 = numeros.removeLast()
        numeros.add(realizarOperacion(operador, num1, num2))
    }

    return numeros[0]
}

fun realizarOperacion(operador: Char, num1: Double, num2: Double): Double {
    return when (operador) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> num1 / num2
        '%' -> num1 % num2
        else -> throw IllegalArgumentException("Operador no válido")
    }
}