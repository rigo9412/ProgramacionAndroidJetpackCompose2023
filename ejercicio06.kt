fun determinar(n1: Int, n2: Int, n3: Int): Boolean {
    val valores = listOf(n1,n2,n3).map { (it%10).toString() }
    val recorrer = arrayOf(n1,n2,n3).map { (it / 10).toString() }
    return recorrer.any { valores.any { a -> it.contains(a) } }
}

fun main(){
    println(determinar(123, 456, 389))
}