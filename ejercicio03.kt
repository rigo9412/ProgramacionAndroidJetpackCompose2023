//Elaborar una función que reciba un texto y cuente el número de letras que contiene.


fun main (){
    println(contar("Hola como estas?"))
}
fun contar(palabra: String): String?{
    var contador = 0    
    for(x in palabra.indices){   
     if(palabra[x].isLetter()){           
      contador = contador + 1    
      }
    }
    return contador.toString()
} 







    

