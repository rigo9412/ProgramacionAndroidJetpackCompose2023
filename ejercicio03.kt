//Elaborar una función que reciba un texto y cuente el número de letras que contiene.

fun main() {
    contarLetras("?Hol4 MunDO 4Andr01d K0TTTlin+ ++-")
}

fun contarLetras(texto:String?){
    if(texto==null) return;
    var (letras)=texto.partition {it.isLetter()}
    var totalLetras= letras.count()
    print("La cantidad de letras es: "+ totalLetras + "\nLas letras son:" + letras)
}