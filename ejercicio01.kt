//Elaborar una función que reciba una cadena de texto y la invierta.

// fun main() {
//     invertidostr("Holaaaa")
// }

 fun main() {
//     invertidostr("Hello, world!!!")
//     invertidostr(null)
//     invertidostr()
    var str = "Rene_Alexis_salinas_a"
    print(str.lowercase().CamelStr())
 }

// fun invertidostr(value: String? = "default"){
//     if(value == null) return;
//     for(item in value.lastIndex downTo 0){
//         print(value[item])
//     }
// }

// Elaborar una función que convierta un texto de camelCase 
// Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"

val cadena = "_[a-zA-Z]".toRegex()

fun String.CamelStr(): String {
    return cadena.replace(this) { 
        it.value.replace("_","")
            .uppercase() 
    }
    
}