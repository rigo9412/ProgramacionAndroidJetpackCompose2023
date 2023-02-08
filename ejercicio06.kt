fun determinar(n1: Int, n2: Int, n3: Int): Boolean {
    val valores = listOf(n1,n2,n3).map { (it%10).toString() }
    val recorrer = arrayOf(n1,n2,n3).map { (it / 10).toString() }
    return recorrer.any { valores.any { a -> it.contains(a) } }
}

//Supongo sin tanto map o any  es mas rapido
fun detemrminar2(n1: Int, n2: Int, n3: Int): Boolean {
    val nn1 = (n1%10).toString()
    val nn2 = (n2%10).toString()
    val nn3 = (n3%10).toString()
    val s1 = n1.toString().contains(nn2) || n1.toString().contains(nn3)
    val s2 = n2.toString().contains(nn1) || n2.toString().contains(nn3)
    val s3 = n3.toString().contains(nn1) || n3.toString().contains(nn2)
    return s1 || s2 || s3
}

fun main(){
    println(detemrminar2(1, 4, 4))
}