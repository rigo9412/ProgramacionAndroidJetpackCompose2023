fun main() {
   println(ultimoDigito(278,617,288))
}

fun ultimoDigito(numero1:Int,numero2:Int,numero3:Int): Boolean {
    val result = setOf(numero1%10, numero2%10, numero3%10).size == 3
    if (result){
        return false
    }else{
        return true
    }
}
