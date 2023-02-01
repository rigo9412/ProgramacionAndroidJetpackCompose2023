//Elaborar una función que convierta un texto de camelCase 

fun main() {
    println(CamelString("Esto es una Cadena"))
    println(CamelString(null))
}

fun CamelString(str1 : String?) : String?{
    if(str1 == null) {
        return "nulo"
    }
    
    var strCounter = 0
    var convertedString = ""

    while(strCounter < str1.length){
        if(str1[strCounter] == ' ' || str1[strCounter] == '-'  || str1[strCounter] == '_'){
            convertedString = convertedString + str1[strCounter+1].uppercase()
            strCounter += 2
            continue
        }
        else{
            convertedString = convertedString + str1[strCounter].lowercase()
            strCounter += 1
        }
    }
    
    convertedString = convertedString.replaceFirstChar{it.lowercase()}
    return convertedString
}