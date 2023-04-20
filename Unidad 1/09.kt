////EJERCICIO 9
////Elaborar una función que aplica las siguientes reglas, 
////ingresar un número entero N positivo y si es N es par lo dividimos y si es impar lo multiplicamos por 3 y lesumamos 1,
//// esta función se deberá detener cuando llegue a 1.
///a) Modificar la función anterior y contar el número de pasos que le tomo llegar a 1, contar los números pares e impares.
///b) Modificar la función anterior para que reciba un segundo número entero y buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,detener el proceso e indicar en que paso lo encontró.

fun aquiGeneroElString(n:Long, segundoNumero:Long) : String {
    proceso(n, segundoNumero, 0, 1)
    return ""
}

fun aplicarJerarquiaInstruccion(instruccion: String): String {
    val elementos = instruccion.split(" ").filter { it.isNotBlank() }
    val longitud = elementos.size
    if (elementos.contains("(")) {
        for (pos in elementos.indices) {
            val elemento = elementos[pos]

            if (elemento == "(") {
                var reemplazos = pos + 1
                var operacionEntreParentesis = ""
                var auxElemento: String
                do {
                    auxElemento = elementos[reemplazos]
                    operacionEntreParentesis += "$auxElemento "
                    reemplazos++
                } while (auxElemento != ")")

                operacionEntreParentesis = operacionEntreParentesis.replace(") ", "")

                val elementosDentroDeParentesis = reemplazos - pos - 1

                elementos[pos++] = aplicarJerarquiaInstruccion(operacionEntreParentesis)

                while (elementosDentroDeParentesis-- > 0) elementos.removeAt(pos)
            }
        }
    }    
        
    if (elemento == "+" || elemento == "-") {
        val operacionPK = generarTokenOperacion(false)
        auxOperacionesRealizadas.add("$operacionPK = ${elementos[pos - 1]} $elemento ${elementos[pos + 1]}")

        elementos[pos - 1] = operacionPK

        elementos.removeAt(pos)
        elementos.removeAt(pos--)
    }

    return elementos[0]
}



fun proceso(n:Long, segundoNumero:Long, pares:Int, impares:Int): Int {
    if(segundoNumero == n) {
        var pasos = pares + impares + 1
        println("SE ENCONTRO EN EL NUMERO " + segundoNumero + " EN EL PASO:" + pasos)
        print("PASO=" + pasos + ",PARES=" + pares + ",INPARES=" + impares)
        return pasos
    } 
    if(n == 1L) {
        var pasos = pares + impares + 1
        println("NO SE ENCONTRO EL NUMERO " + segundoNumero)
        print("PASO=" + pasos + ",PARES=" + pares + ",INPARES=" + impares)
        return pasos
    }

    if(n%2 == 0L) {
        return proceso(n / 2, segundoNumero, pares + 1, impares)
    } else {
        return proceso(n * 3 + 1, segundoNumero, pares, impares+1)
    }
}

fun main() {
    println(aquiGeneroElString(10_017_019_990_047_100, 55))
}