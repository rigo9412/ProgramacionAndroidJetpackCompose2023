fun main(){
    //println("Hola mundo :3  ")
    volteo("Hello, world!!!")
}
fun volteo(palabra: String){

    var palabra2 = ""

    for( i in palabra.length-1 downTo 0)
    {
        palabra2 = palabra2.plus(palabra[i])
    }
    println("palabra original: " + palabra)
    println("palabra volteada: " + palabra2)

}