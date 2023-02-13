fun main() {
    //Llama a la funcion ContarLetras enviando un parametro string
  ContarLetras("?Hol4 MunDO 4Andr01d K0TTTlin+ ++-")
}
fun ContarLetras (Palabra: String) {   
    
    //Crea un Arreglo(Array) de caracteres de un string
    var lisCaracter = Palabra.toCharArray()
    var ASCII = 0
    var Contador = 0    
    
    for(lisCaracter in lisCaracter){
        
    //Convertir cada caracter en su valor ASCII
    	ASCII = lisCaracter.toInt()
            
        if((ASCII >= 65 && ASCII<=90) || (ASCII >= 97 && ASCII<=122)){
            //Incrementar Contador siempre que sea verdadero
      	Contador++
        } 
    }
    print(Contador)
}