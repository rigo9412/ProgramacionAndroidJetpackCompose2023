//Elaborar una función que convierta un texto de camelCase 
//Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"

fun main(){
    println(camelCase("Hello World !!"))
    println(camelCase("Esto es una Cadena"))
}

fun camelCase(str1 : String?) : String?{
    if(str1 == null) {
        return "nulo"
    }
    var strCounter = 0
    var convertedString = "";
    while(strCounter < str1.length)
    {
        if(str1[strCounter] == ' ')
        {
            convertedString = convertedString + str1[strCounter+1].uppercase()
            strCounter += 2;
            continue
        }
        else
        {
            convertedString = convertedString + str1[strCounter]
            strCounter += 1
        }
    }
    convertedString = convertedString.replaceFirstChar{it.lowercase()
    }
    return convertedString
}