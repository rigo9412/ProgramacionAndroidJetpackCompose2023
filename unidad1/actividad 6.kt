
fun main() {
    println(busquedaNum(1,12,3))
}


fun busquedaNum(num1: Int, num2: Int, num3: Int): Boolean {

    var residuo1 = (num1 % 10).toString()
    var residuo2 = (num2 % 10).toString()
    var residuo3 = (num3 % 10).toString()

    var rep = (num1/10).toString() + (num2/10).toString() + (num3/10).toString()

    return (rep.contains(residuo1) || rep.contains(residuo2) || rep.contains(residuo3)) ||
            (residuo1 == residuo2 || residuo1 == residuo3 || residuo2 == residuo3)
}

