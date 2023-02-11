/*Elaborar una función que aplica las siguientes reglas, ingresar un número entero N
positivo y si es N es par lo dividimos y si es impar lo multiplicamos por 3 y le
sumamos 1, esta función se deberá detener cuando llegue a 1.

a. Modificar la función anterior y contar el número de pasos que le tomo llegar a 1, contar los números pares e impares.

b. Modificar la función anterior para que reciba un segundo número entero y
buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,
detener el proceso e indicar en que paso lo encontró.*/

fun main() {
    var numero1: Int
    print("Ingrese el numero 1: ")
    numero1 = readLine()!!.toInt()

    var numero2 :Int
    print("Ingrese el numero 2: ")
    numero2 = readLine()!!.toInt()

    print(parImparB(numero1,numero2))
}
fun parImparB(numero:Int,Num:Int): String {
    var N = numero

    var i = 1
    var pasos = 1



    while (N != i) {
        if (N % 2 == i) {
            N = (N * 3) + 1

            pasos++

        } else
            N /= 2

        pasos++

        if(N == Num) return "El numero se encontro "+Num+" en el paso:"+pasos

    }
    return ("No se encontro en el numero $Num")
}