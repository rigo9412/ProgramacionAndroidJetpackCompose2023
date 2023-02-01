fun main() {
    stringInvert("holii")
}

fun stringInvert(value: String? = "default"){
    if(value==null) return; 
    /*for(item in value.lastIndex downTo 0){
        print(value[item])
    }*/
    val letras : CharArray = value.toCharArray()
    var n = value.length - 1
    while (n>=0)
    {
        print(letras[n])
        n--
    }
}