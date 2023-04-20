////EJERCICIO 10
////Elaborar una función que reciba una operación aritmética en formato texto y que laresuelva, la operación solicitada son suma, resta, división, producto, residuo y porcentaje.
///a) Agregar funcionalidad de jerarquía de operadores
///b) Agregar funcionalidad de raíz cuadrada.
fun resolverEcuacion(ecuacion: String) : Double {
    var resultado: Double = 0.0

    //Recorrer cada elemento del string
    //Si es un numero, o un punto ve acumulandolo
    //Cuando sea distinto, convierte el string acumulado a un caracter
    
    //Separar operaciones con espacios
    var ecEspacios = ecuacion.replace("+", " + ").replace("-", " - ").replace("*", " * ").replace("/", " / ").replace("m", " m ").replace("s", " s ").replace("p", " p ").replace("  "," ")
    println(aplicarJerarquiaInstruccion(ecEspacios))

    //Ordenar jerarquicamente cada una de las operaciones (por tripletas)


    return resultado
}

fun aplicarJerarquiaInstruccion(instruccion: String): String {
    val elementos = instruccion.split(" ").filter { it.isNotBlank() }
    val longitude = elementos.size

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

                while (elementosDentroDeParentesis-- > 0) {
                    elementos.remove(elemento)
                } 
            }
        }
    }






    for(el in elementos) {
        //A la par que se va ordenando, si es un numero, reemplaza los puntos
        println(el)
    }

    return ""
}

fun reemplazarPunto(numero: String): String {
    
    if(numero[0] == '.')
        return "0" + numero
   
    return numero
}
// fun aplicarJerarquiaInstruccion(instruccion: String): String {
//     val elementos = instruccion.split(" ").filter { it.isNotBlank() }
//     val longitud = elementos.size
//     if (elementos.contains("(")) {
//         for (pos in elementos.indices) {
//             val elemento = elementos[pos]

//             if (elemento == "(") {
//                 var reemplazos = pos + 1
//                 var operacionEntreParentesis = ""
//                 var auxElemento: String
//                 do {
//                     auxElemento = elementos[reemplazos]
//                     operacionEntreParentesis += "$auxElemento "
//                     reemplazos++
//                 } while (auxElemento != ")")

//                 operacionEntreParentesis = operacionEntreParentesis.replace(") ", "")

//                 val elementosDentroDeParentesis = reemplazos - pos - 1

//                 elementos[pos++] = aplicarJerarquiaInstruccion(operacionEntreParentesis)

//                 while (elementosDentroDeParentesis-- > 0) elementos.removeAt(pos)
//             }
//         }
//     }
    
        
//     if (elemento == "+" || elemento == "-") {
//         val operacionPK = generarTokenOperacion(false)
//         auxOperacionesRealizadas.add("$operacionPK = ${elementos[pos - 1]} $elemento ${elementos[pos + 1]}")

//         elementos[pos - 1] = operacionPK

//         elementos.removeAt(pos)
//         elementos.removeAt(pos--)
//     }

//     return elementos[0]
// }


fun main() {
    println(resolverEcuacion("5*         15 - 2.0/.4")) //74.5
}