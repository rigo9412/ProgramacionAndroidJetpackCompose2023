//Elaborar una función que reciba una cadena de texto y la invierta.

fun main() {
    println(stringInvert("Hello,world!!!"));
    println(ReverseString("Jordan Yo"))
}

fun stringInvert(value:String):String{
    if (value==null) return "Cadena vacia";
    var newString:String=""
    for(item in value.length-1 downTo 0){
       newString+=value[item]
    }
    return newString
}


fun ReverseString(value:String):String{
    if (value==null) return "Cadena vacia";
    var newString:String=""
    var i:Int = value.lastIndex+1
    while(--i >=0){
        newString+=value[i]
    }
    return newString
}