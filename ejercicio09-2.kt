fun main() {
   print(nameless(10_017_019_990_047_100,11272))
}

fun nameless(NumInicial : Long,Num : Long): String {
    var N = NumInicial ; var i = 1.toLong()
    var pasos = 1
        while (N != i){
        if (N%2 == i){
            N = (N*3)+1
            pasos++
        }
            N = N/2
            pasos++
            if(N == Num) return "SE ENCONTRO EN EL NUMERO "+Num+" EN EL PASO:"+pasos
    }
    return "NO SE ENCONTRO EN EL NUMERO "+Num
}