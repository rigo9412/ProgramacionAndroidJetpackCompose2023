//Elaborar una función que reciba una cadena de texto y la invierta.

fun main() {
     stringInvert("Hello, world!!!")
    }
    fun stringInvert(value: String? = "default")
    { if (value == null) return;
        
    for(item in value.lastIndex downTo 0)
    { 
        print(value[item]) 
    }
}
