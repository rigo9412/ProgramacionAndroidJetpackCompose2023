//Elaborar una función que reciba 3 números enteros y que determine si el último
//dígito del número se repite entre alguno de los 3 números.
fun main(args: Array<String>) {
    var numero1: String
    var numero2: String
    var numero3: String

    print("Ingrese el numero 1: ")
    numero1 = readLine()!!.toString()
    print("Ingrese el numero 2: ")
    numero2 = readLine()!!.toString()
    print("Ingrese el numero 3: ")
    numero3 = readLine()!!.toString()

    print(determinarSiExistenNumerosRepetidos(numero1,numero2,numero3))
}

fun determinarSiExistenNumerosRepetidos(numero1:String,numero2:String,numero3:String): Boolean {
    val ultimoDigito1 = numero1.takeLast(1)
    val ultimoDigito2 = numero2.takeLast(1)
    val ultimoDigito3 = numero3.takeLast(1)
    
    val ultimosDigitos1 = numero2.takeLast(1) + numero3.takeLast(1)
    val ultimosDigitos2 = numero1.takeLast(1) + numero3.takeLast(1)
    val ultimosDigitos3 = numero1.takeLast(1) + numero2.takeLast(1)
    
    val Existe1 = (numero1.dropLast(1)).contains(ultimoDigito1) || (numero2.dropLast(1)).contains(ultimoDigito1) || (numero3.dropLast(1)).contains(ultimoDigito1) || (ultimosDigitos1.contains(ultimoDigito1))
    var Existe2 = (numero1.dropLast(1)).contains(ultimoDigito2) || (numero2.dropLast(1)).contains(ultimoDigito2) || (numero3.dropLast(1)).contains(ultimoDigito2) || (ultimosDigitos2.contains(ultimoDigito2))
    var Existe3 = (numero1.dropLast(1)).contains(ultimoDigito3) || (numero2.dropLast(1)).contains(ultimoDigito3) || (numero3.dropLast(1)).contains(ultimoDigito3) || (ultimosDigitos3.contains(ultimoDigito3))

    return (Existe1 || Existe2 || Existe3)
}