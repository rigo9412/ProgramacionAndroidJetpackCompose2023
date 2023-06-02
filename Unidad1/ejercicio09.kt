/*
Elaborar una función que aplica las siguientes reglas, ingresar un número entero N
positivo y si es N es par lo dividimos y si es impar lo multiplicamos por 3 y le
sumamos 1, esta función se deberá detener cuando llegue a 1.
a. Modificar la función anterior y contar el número de pasos que le tomo llegar a
1, contar los números pares e impares.
b. Modificar la función anterior para que reciba un segundo número entero y
buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,
detener el proceso e indicar en que paso lo encontró.
 */

fun main(){
    println(collatzPasos(10_017_019_990_047_100))
    println(collatzEncontrar(10_017_019_990_047_100, 11272))
}

fun collatz(numero : Long): Long{
    var num = numero
    while(num != 1L){
        if(num % 2 == 0L){
            num = num / 2
        }
        else{
            num = num * 3 + 1
        }
    }
    return 1
}

fun collatzPasos(numero : Long) : String{
    var num = numero
    var par = 0L
    var impar = 0L

    while(num != 1L){
        if(num % 2 == 0L) {
            num = num / 2
            par += 1
        }
        else {
            num = num * 3 + 1
            impar += 1
        }
    }
    return("PASO=${par+impar},PARES=$par,IMPARES=$impar")
}

fun collatzEncontrar(numero : Long, detener : Long) : String{
    var num = numero
    var pasos = 0

    while(num != 1L){
        if(num % 2 == 0L) {
            num = num / 2
        }
        else {
            num = num * 3 + 1
        }
        pasos += 1

        if(num == detener){
            return("SE ENCONTRO EL NUMERO $num EN EL PASO $pasos")
        }
    }
    return("NO SE ENCONTRO EL NUMERO=$detener")
}