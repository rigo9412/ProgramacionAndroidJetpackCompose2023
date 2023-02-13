//19100140 Ejercicio 9
/*Elaborar una función que aplica las siguientes reglas, ingresar un número entero N
positivo y si es N es par lo dividimos y si es impar lo multiplicamos por 3 y le
sumamos 1, esta función se deberá detener cuando llegue a 1.
a. Modificar la función anterior y contar el número de pasos que le tomo llegar a
1, contar los números pares e impares.
b. Modificar la función anterior para que reciba un segundo número entero y
buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,
detener el proceso e indicar en que paso lo encontró.
*/
//

fun main(){

    println(reduccionAUno(7))
    println(reduccionAUnoA(7))
    println(reduccionAUnoB(7,16))
}


fun reduccionAUno(n: Long): String{
    var numero = n
    //println(numero)

    while (numero != 1.toLong())
    {
        numero = if (numero % 2.toLong() == 0.toLong())
        {
            numero / 2.toLong()
        }
        else
        {
            numero * 3.toLong() + 1.toLong()
        }
        //println(numero)
    }
    return (numero.toString())
}



fun reduccionAUnoA(n: Long): String {
    var numero = n
    var pasos = 0.toLong()
    var par = 0.toLong()
    var impar = 0.toLong()

    while (numero != 1.toLong())
    {

        if (numero % 2 == 0.toLong())
        {
            numero /= 2
            par++
        }
        else
        {
            numero = numero * 3 + 1
            impar++
        }
        pasos++
    }

    var res = Pair(pasos, Pair(par, impar))

    return ("PASO=" + res.first.toString() + ",PARES="+res.second.first.toString()+",IMPARES="+res.second.second.toString())
}



fun reduccionAUnoB(n: Long, secundario: Long): String {

    var numero = n
    var pasos = 0.toLong()
    var par = 0.toLong()
    var impar = 0.toLong()

    while (numero != 1.toLong())
    {
        var res = Pair(pasos, Pair(par, impar))
        if (numero == secundario)
        {
            return ("PASO=" + res.first.toString() + ",PARES="+res.second.first.toString()+",IMPARES="+res.second.second.toString())
        }

        if (numero % 2 == 0.toLong())
        {
            numero /= 2
            par++
        }
        else
        {
            numero = numero * 3 + 1
            impar++
        }
        pasos++
    }

    var res = Pair(pasos, Pair(par, impar))
    return ("PASO=" + res.first.toString() + ",PARES="+res.second.first.toString()+",IMPARES="+res.second.second.toString())
}