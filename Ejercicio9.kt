E j e r c i c i o    9

fun main() {
    println(ParImpar1(10_017_019_990_047_100))
}

fun ParImpar1(num:Long): Long {
    var va = num
    var i = 1.toLong()
    while (va != i){
        if(va%2 == i) va = (va * 3) + 1
        else va = va / 2
    }
    return va
}




E j e r c i c i o    9 a


fun main() {
    println(ParImpar2(10_017_019_990_047_100))
}

fun ParImpar2(num:Long): String {
    var va = num
    var i = 1.toLong()
    var pasos = 1
    var impar = 1 
    var par = 0
    while (va != i){
        if(va%2 == i){
            va = (va * 3) + 1
            impar++
            pasos++
        } 
        else{
          va = va / 2
          par++
          pasos++
        } 
    }
    return "PASO="+pasos+",PARES="+par+",IMPARES="+impar
}




E j e r c i c i o    9 b


fun main() {
    println(ParImpar3(10_017_019_990_047_100,10_017_019_990_047_100))
}

fun ParImpar3(num:Long, buscarNum:Long): String {
    var va = num
    var i = 1.toLong()
    var pasos = 1
    while (va != i){
        if(va == buscarNum) return "SE ENCONTRÓ EN EL NÚMERO "+buscarNum+" EN EL PASO:"+pasos
        else if(va%2 == i){
            va = (va * 3) + 1
            pasos++
        } 
        else{
          va = va / 2
          pasos++
        }
        if(va == buscarNum) return "SE ENCONTRÓ EN EL NÚMERO "+buscarNum+" EN EL PASO "+pasos
    }
    return "NO SE ENCONTRÓ EN EL NÚMERO "+buscarNum
}
