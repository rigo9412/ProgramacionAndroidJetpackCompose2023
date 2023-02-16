
fun main(){
    print(calcularOperacionAritmetica("10*2-3/2"))
    
}

fun calcularOperacionAritmetica(operacion: String): Double {
    val numeros = mutableListOf<Double>()
    val operadores = mutableListOf<Char>()
    var numeroActual = ""
    for (caracter in operacion) {
        when {
            caracter.isDigit() || caracter == '.' -> {
                numeroActual += caracter
            }
            caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/' || caracter == '%' -> {
                numeros.add(numeroActual.toDouble())
                numeroActual = ""
                operadores.add(caracter)
            }
            caracter == '√' -> {
                operadores.add(caracter)
            }
        }
    }
    numeros.add(numeroActual.toDouble())

    // Resolver raíces cuadradas
    var i = 0
    while (i < operadores.size) {
        if (operadores[i] == '√') {
            val numero = numeros[i+1]
            numeros[i+1] = Math.sqrt(numero)
            numeros.removeAt(i)
            operadores.removeAt(i)
        } else {
            i++
        }
    }

    // Resolver multiplicaciones y divisiones
    i = 0
    while (i < operadores.size) {
        if (operadores[i] == '*' || operadores[i] == '/') {
            val operador = operadores[i]
            val num1 = numeros[i]
            val num2 = numeros[i+1]
            val resultado = if (operador == '*') num1 * num2 else num1 / num2
            numeros[i] = resultado
            numeros.removeAt(i+1)
            operadores.removeAt(i)
        } else {
            i++
        }
    }

    // Resolver sumas y restas
    var resultado = numeros[0]
    for (x in 0 until operadores.size) {
        val operador = operadores[x]
        val numero = numeros[x+1]
        resultado = when (operador) {
            '+' -> resultado + numero
            '-' -> resultado - numero
            else -> resultado
        }
    }

    return resultado
}
