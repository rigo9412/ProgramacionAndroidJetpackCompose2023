fun main(){

    val Palabra = "Hola     MUNdo-Kotlin_Android-Jet_Pack"
    println(Palabra)

    //Convierte el string en minusculas
    val Minusculas = Palabra.lowercase()
    println(Minusculas)

    //Separa siempre que vea ' ',-,_ y agregar a una lista
    var lisPalabra = Minusculas.split(" ","-","_")
    var res = ""
    
    //Convertir en mayuscula el primer caracter de cada string de la lista
    for(i in 0..9){
        res = res + lisPalabra[i].replaceFirstChar{
            it.uppercase()
        }
    }
    
    //convertir el primer caracter del string en minuscul
    res = res.replaceFirstChar{
        it.lowercase()
    }
    print(res)

}