/*
Elaborar una función que aplica las siguientes reglas, ingresar un número entero N positivo y si es N es par lo dividimos y
si es impar lo multiplicamos por 3 y le sumamos 1, esta función se deberá detener cuando llegue a 1.

a.Modificar la función anterior y contar el número de pasos que le tomo llegar a 1, contar los números pares e impares.

b.Modificar la función anterior para que reciba un segundo número entero y buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,
detener el proceso e indicar en que paso lo encontró.

 */
fun funcionCount(n: Long): Long{
    var numero: Long = n
    while(numero != 1L) if(numero % 2 == 0L){
        numero /= 2
    }else{
        numero = numero * 3 + 1
    }
    return 1
}

fun funcionFind(n: Long, parar: Long): String{
    var contador = 0L
    var contadorPares = 0L
    var contadorImpares = 0L
    var numero = n
    while(numero != 1L){
        if(numero == parar){
            contador++
            return "SE ENCONTRO EL NUMERO $parar EN EL PASO $contador"
        }
        if(numero % 2 == 0L){
            numero /= 2
            contadorPares++
        }else{
            numero = numero * 3 + 1
            contadorImpares++
        }
        contador++
    }
    return "NO SE ENCONTRO EN EL NUMERO $n"
}

fun funcionPasos(n: Long): String{
    var contador: Long = 1L
    var contadorPares: Long = 0L
    var contadorImpares: Long = 1L
    var numero: Long = n
    while(numero != 1L){
        if(numero % 2 == 0L){
            numero /= 2
            contadorPares++
        }else{
            numero = numero * 3 + 1
            contadorImpares++
        }
        contador++
    }
    return "PASO=$contador,PARES=$contadorPares,INPARES=$contadorImpares"
}

fun main() {
    println(funcionFind(10_017_019_990_047_100, 11272))
}