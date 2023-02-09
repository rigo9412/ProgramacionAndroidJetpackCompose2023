fun main() {
    print(ultimoDigito(1,1,1))
}

fun ultimoDigito(num1: Int, num2: Int, num3: Int): Boolean {
    var aux = num1.toString().split("").dropLast(1)
    var res1 = aux.drop(1)
    aux = num2.toString().split("").dropLast(1)
    var res2 = aux.drop(1)
    aux = num3.toString().split("").dropLast(1)
    var res3 = aux.drop(1)

    if(res1.size == 1 && res2.size == 1 && res3.size == 1 ) return false;

    val arr = arrayOf(res1[2], res2[2], res3[2])

    var cut1 = res1.dropLast(1)
    var cut2 = res2.dropLast(1)
    var cut3 = res3.dropLast(1)

    if(cut1.contains(arr[0]) || res2.contains(arr[0]) || res3.contains(arr[0])) return true;
    if(res1.contains(arr[1]) || cut2.contains(arr[1]) || res3.contains(arr[1])) return true;
    if(res1.contains(arr[2]) || res2.contains(arr[2]) || cut3.contains(arr[2])) return true;

    return false
}