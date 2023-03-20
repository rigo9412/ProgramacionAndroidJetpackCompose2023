import kotlin.math.sqrt

fun main(){
    println(Operacion("5*(9+(10/2))"))
}


fun Operacion(operacion: String): Double {
    val listaNumeros = mutableListOf<Double>()
    val listaOperadores = mutableListOf<Char>()
    var indice = 0

    while (indice < operacion.length) {
        when {
            operacion[indice].isDigit() -> {
                var numStr = ""
                while (indice < operacion.length && (operacion[indice].isDigit() || operacion[indice] == '.')) {
                    numStr += operacion[indice++]
                }
                listaNumeros.add(numStr.toDouble())
            }
            operacion[indice] == '+' || operacion[indice] == '-' || operacion[indice] == '*' || operacion[indice] == '/'|| operacion[indice] == 'R' || operacion[indice] == '%' || operacion[indice] == 'S' -> {
                listaOperadores.add(operacion[indice++])
            }
            operacion[indice] == '(' -> {
                listaOperadores.add(operacion[indice++])
            }
            operacion[indice] == ')' -> {
                while (listaOperadores.last() != '(') {
                    if (listaOperadores.last() == 'S'){
                        val operador = listaOperadores.removeLast()
                        val num1 = listaNumeros.removeLast()
                        listaNumeros.add(CalcOPeraciones(operador, num1))
                    } else {
                        val operador = listaOperadores.removeLast()
                        val num2 = listaNumeros.removeLast()
                        val num1 = listaNumeros.removeLast()
                        listaNumeros.add(CalcOPeraciones(operador, num1, num2))
                    }
                }
                listaOperadores.removeLast()
                indice++
            }
            operacion[indice] == ' ' -> {
                indice++
            }
            else -> {
                throw IllegalArgumentException("\nError: Caracter no válido\n")
            }
        }
    }

    while (listaOperadores.isNotEmpty()) {
        if (listaOperadores.last() == 'S'){
            val operador = listaOperadores.removeLast()
            val num1 = listaNumeros.removeLast()
            listaNumeros.add(CalcOPeraciones(operador, num1))
        } else{
            val operador = listaOperadores.removeLast()
            val num2 = listaNumeros.removeLast()
            val num1 = listaNumeros.removeLast()
            listaNumeros.add(CalcOPeraciones(operador, num1, num2))
        }
    }

    return listaNumeros[0]
}

fun CalcOPeraciones(operador: Char, num1: Double, num2: Double = 0.0): Double {
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