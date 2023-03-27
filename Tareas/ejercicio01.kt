//Elaborar una función que reciba una cadena de texto y la invierta.

fun main() {
    println(ReverseString("Hola mundo"))
    println(ReverseString(null))
}

fun ReverseString(str1 : String?) : String?{
    if(str1 == null) {
        return "nulo"
    }
    
    var strLen = str1.length
    var reversedStr = ""
    
    while(strLen > 0){
    	reversedStr = reversedStr + str1[strLen-1] 
        strLen -= 1
    }
    
    return reversedStr
}