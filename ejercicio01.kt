//Elaborar una función que reciba una cadena de texto y la invierta.

fun main() {
  strInvert("Hola")
}

fun stringInvert(value: String? = "default") {

  if(value == null) return;
  for(item in value.lastIndex downTo 0){
    print(value[item])
  }
}

fun strInvert(value: String? = "default"){
    if(value == null) return;
    
	while( value.lastIndex >= 0 ){
        print(value[i])
        i = i-1
    }
}