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
   
    reduccionAUno(7)
    println(reduccionAUnoA(7).toString())
    
}


fun reduccionAUno(n: Int) {
    var numero = n
    println(numero)
    
    while (numero != 1) 
    {
        numero = if (numero % 2 == 0) 
        {
            numero / 2
        } 
        else 
        {
            numero * 3 + 1
        }
        println(numero)
    }
    
}



fun reduccionAUnoA(n: Int): Pair<Int, Pair<Int, Int>> {
    var numero = n
    var pasos = 0
    var par = 0
    var impar = 0
    
    while (numero != 1) 
    {
        
        if (numero % 2 == 0) 
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
    
    return Pair(pasos, Pair(par, impar))
}



fun reduccionAUnoB(n: Int, secundario: Int): Pair<Int, Pair<Int, Int>> {
    
    var numero = n
    var pasos = 0
    var par = 0
    var impar = 0
    
    while (numero != 1) 
    {
        
        if (numero == secundario) 
        {
            return Pair(pasos, Pair(par, impar))
        }
        
        if (numero % 2 == 0) 
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
    
    return Pair(pasos, Pair(par, impar))
}