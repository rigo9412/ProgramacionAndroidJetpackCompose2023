//9. Elaborar una función que aplica las siguientes reglas: ingresar un número entero N
//positivo y si N es par lo dividimos y si es impar lo multiplicamos por 3 y le sumamos 1,
// esta función se deberá detener cuando llegue a 1.
//a. Modificar la función anterior y contar el número de pasos que le tomo llegar a 1, contar los números pares e impares.

//b. Modificar la función anterior para que reciba un segundo número entero y buscar si existe dentro de los pasos para llegar al número 1
// y si lo encuentra, detener el proceso e indicar en que paso lo encontró.
fun main(args: Array<String>) {
    var numN = 50
    parImpar(numN)
}

fun parImpar(numN : Int) : Int{
    var nuevoNumero = numN
    var pasos = 0
    var contPar = 0
    var contImpar = 0
    while (nuevoNumero != 1)
    {
        if (nuevoNumero % 2 == 0 ) {
            nuevoNumero /= 2
            contPar += 1
            println("Par: El nuevo numero es: $nuevoNumero se dividio")
        }
        else{
            nuevoNumero = (nuevoNumero * 3) + 1
            contImpar += 1
            println("Impar: El nuevo numero es: $nuevoNumero se multiplico y sumo 1")
        }
        pasos++
    }
    println("---------------------------------------")
    println("Cantidad de pasos $pasos")
    println("Cantidad de pasos impares $contImpar")
    println("Cantidad de pasos pares $contPar")
    return numN
}
