//Elaborar una función que convierta un texto de camelCase 
// Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"

fun main() {
    println(ConvertToCamelCase("Esto es una Cadena"))
}

fun ConvertToCamelCase(value:String):String {
   
   var newStr = value.split(" ")
    var words:String
    var outStr:String=""
   for(letter in newStr){
    if(letter==newStr[0]){
        words = letter[0].lowercase() + letter.substring(1)
    }
    else{
         words = letter[0].uppercase() + letter.substring(1)
    }
    outStr+=words
   }
   
   return outStr
}