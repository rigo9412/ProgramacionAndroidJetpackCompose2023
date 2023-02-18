/*Elaborar una función que aplica las siguientes reglas, ingresar un número entero Npositivo y si es N es par lo dividimos 
y si es impar lo multiplicamos por 3 y lesumamos 1, esta función se deberá detener cuando llegue a 1.

fun main(args: Array<String>) {
    println(aplicarRegla(10))
}

fun aplicarRegla(numero: Long): Long{
    print(esNumeroValido(numero))
    var auxiliar = 1.toLong()
    var N = numero
    while (N != auxiliar){
        if (N%2 == auxiliar) { N = (N*3)+1 }
        N /= 2
    }
    return N
}

fun esNumeroValido(numero: Long): String{
    if (numero <=0 )
        return("El numero ingresado no es positivo")
    else if(numero.toString().toDoubleOrNull() == null)
        return("El numero ingresado no es entero")
    else return("El numero ingresado es valido")
}