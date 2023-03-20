fun main(){
    ContadordeLetras("?Hol4 MunDO 4Andr01d K0TTTlin+ ++-")
}

fun ContadordeLetras(value: String): Unit{
    var lets = Regex("[^A-Za-z]").replace(value,"").length
    print(lets)
}