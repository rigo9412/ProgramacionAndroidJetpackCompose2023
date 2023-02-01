//Elaborar una función que reciba una cadena de texto y la invierta.

fun main() {
    invertirString("Perro")
}

fun invertirString (value: String?) 
{
	if (value==null) return;
    
    	var x = value.reversed()
    	print(x)
}
