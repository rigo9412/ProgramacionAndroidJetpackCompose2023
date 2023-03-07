// Elaborar una funcion que reciba un texto y cuente el numero de letras que contiene

fun main() {
    contar("Perro")
}

fun contar(value: String){
	var x=value.split("")
    var letras: Int=0
    for (letra in x){
        if (letra!=""){
            letras++
        }
    }
    println("Letras en total $letras")    
}
