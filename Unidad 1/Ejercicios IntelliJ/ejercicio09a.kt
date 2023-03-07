/*a.Modificar la función anterior y contar el número de
pasos que le tomo llegar a1, contar los números pares e impares.*/

fun main(args: Array<String>) {
    println(aplicarRegla(10))
}

fun aplicarRegla(numero: Int): String{
    println(esNumeroValido(numero))
    var N = numero
    var auxiliar = 1
    var impar = 1
    var par = 0
    var pasos = 1

    while (N != auxiliar) {
        if (N % 2 == auxiliar) {
            N = (N * 3) + 1
            impar = impar + 1; pasos = pasos + 1
        } else
            N /= 2
        par = par + 1; pasos = pasos + 1
    }
    return ("NumerosImpares $impar CantidadPasos$pasos \n NumerosPares=$par")
}

fun esNumeroValido(numero: Int): String{
    if (numero <=0 )
        return("El numero ingresado no es positivo")
    else if(numero.toString().toDoubleOrNull() == null)
        return("El numero ingresado no es entero")
    else return("El numero ingresado es valido")
}