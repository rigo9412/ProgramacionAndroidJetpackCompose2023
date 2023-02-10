//Elaborar una función que aplica las siguientes reglas, ingresar un número entero N positivo y si es N es par lo dividimos 
//y si es impar lo multiplicamos por 3 y le sumamos 1, esta función se deberá detener cuando llegue a 1.

//a. Modificar la función anterior y contar el número de pasos que le tomo llegar a1, contar los números pares e impares.

//b. Modificar la función anterior para que reciba un segundo número entero y buscar si existe dentro de los pasos para 
//llegar al número 1 y si lo encuentra,detener el proceso e indicar en que paso lo encontró.

fun main(){
    parOImpar(2)
    println(parOImparA(2).toString())
}

fun parOImpar(n: Int) {
    var x = n
    println(x)
    while (x != 1) 
    {
        x = if (x % 2 == 0) 
        {
            x / 2
        } 
        else 
        {
            x * 3 + 1
        }
        println(x)
    }    
}

fun parOImparA(n: Int): Pair<Int, Pair<Int, Int>> {
    var x = n
    var pasos = 0
    var par = 0
    var impar = 0
    while (x != 1) 
    {
        
        if (x % 2 == 0) 
        {
            x /= 2
            par++
        } 
        else
        {
            x = x * 3 + 1
            impar++
        }
        pasos++
    }
    return Pair(pasos, Pair(par, impar))
}

fun parOImparB(n: Int, secun: Int): Pair<Int, Pair<Int, Int>> {
    var num = n
    var pasos = 0
    var par = 0
    var impar = 0
    while (num != 1) 
    {
        if (num == secun) 
        {
            return Pair(pasos, Pair(par, impar))
        }
        if (num % 2 == 0) 
        {
            num /= 2
            par++
        } 
        else 
        {
            num = num * 3 + 1
            impar++
        }
        pasos++
    }
    return Pair(pasos, Pair(par, impar))
}
