/*Elaborar una función que aplica las siguientes reglas, ingresar un número entero N
positivo y si es N es par lo dividimos y si es impar lo multiplicamos por 3 y le
sumamos 1, esta función se deberá detener cuando llegue a 1.
 */

fun main() {
   print(nameless(10_017_019_990_047_100))
}

fun nameless(NumInicial : Long): String {
    var N = NumInicial ; var i = 1.toLong()
    var pasos = 1; var impar = 1 ;var par = 0
    while (N != i){
        if (N%2 == i){
            N = (N*3)+1
            impar++
            pasos++
        }
        N = N/2
        par++
        pasos++
    }
    return "PASO="+pasos+",PARES="+par+",INPARES="+impar
}