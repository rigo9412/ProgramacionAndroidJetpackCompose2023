fun main(args: Array<String>) {
   
    var arg = arrayOf(32,5,65,7)
    InvertirPrimSegun(arg)
}

fun InvertirPrimSegun(Arreglo: Array<Int>){
    
    var temporal = 0
    
    temporal = Arreglo[0]
    Arreglo[0] = Arreglo[1]
    Arreglo[1] = temporal
    
	for (Arreglo in Arreglo){
        print("$Arreglo, ")
    }
}