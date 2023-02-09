////EJERCICIO 9
////Elaborar una función que aplica las siguientes reglas, 
////ingresar un número entero N positivo y si es N es par lo dividimos y si es impar lo multiplicamos por 3 y lesumamos 1,
//// esta función se deberá detener cuando llegue a 1.
///a) Modificar la función anterior y contar el número de pasos que le tomo llegar a 1, contar los números pares e impares.
///b) Modificar la función anterior para que reciba un segundo número entero y buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,detener el proceso e indicar en que paso lo encontró.

var pasos = 1
var pares = 0
var impares = 1
fun aquiGeneroElString(n:Long, segundoNumero:Long) : String {
    val result = proceso(n, segundoNumero)
    
    pasos = pares + impares
    if(result != -1) {
        println("NO SE ENCONTRO EL NUMERO " + segundoNumero)
    }

    return "PASO=" + pasos + ",PARES=" + pares + ",INPARES=" + impares
}

fun proceso(n:Long, segundoNumero:Long): Int {
    if(segundoNumero == n) {
        pasos = pares + impares
        println("SE ENCONTRO EN EL NUMERO " + segundoNumero + " EN EL PASO:" + pasos)
        return -1
    } 
    if(n == 1L) return 1

    if(n%2 == 0L) {
        pares++
        return proceso(n / 2, segundoNumero)
    } else {
        impares++
        return proceso(n * 3 + 1, segundoNumero)
    }
}

fun main() {
    println(aquiGeneroElString(10_017_019_990_047_100,0))
}