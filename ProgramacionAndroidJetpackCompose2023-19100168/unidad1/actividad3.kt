import java.lang.Character.isLetter

fun main(){
    print("favor de ingresar texto: ")
    //var texto = "pol 0 init + hola"
    var texto = ""
    texto = readLine().toString()
    println(Letra(texto))
}

fun Letra(texto:String) : Int{
    var numLetras = 0
    for (i in 0 .. texto.length-1 ){
        if(texto[i].isLetter()){
            numLetras++
        }
    }
    return (numLetras)
}

