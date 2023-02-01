//Elaborar una función que convierta un texto de camelCase 

fun main() {
    convertirCamelCase("Una Cadena Escrita")
}

fun convertirCamelCase(texto:String?){
    if(texto==null) return;
    var cadena=texto.split(" ")
    var conversion=""
    for (i in cadena.indices) {
        if(i==0){
         conversion+=cadena[i].replaceFirstChar {it.lowercase()}
        }
        else{
            conversion+=cadena[i].replaceFirstChar {it.uppercase()}
        }
        
     
    }
    print(conversion)}