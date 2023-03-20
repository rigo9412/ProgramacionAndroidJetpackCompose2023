fun main(){
    val r = 20
    secuen(r)
    contSecuen(r)
    altSecuen(r,4)
}
fun secuen(n: Int) {
    var number = n
    while (number != 1) {
        println(number)
        number = if (number % 2 == 0) number / 2 else 3 * number + 1
    }
    println(number)
}

fun contSecuen(n: Int) {
    var number = n
    var stepCount = 0
    var evenCount = 0
    var oddCount = 0
    while (number != 1) {
        println(number)
        stepCount++
        if (number % 2 == 0) {
            evenCount++
            number /= 2
        } else {
            oddCount++
            number = 3 * number + 1
        }
    }
    println(number)
    println("Movimientos: $stepCount")
    println("Pares: $evenCount")
    println("Impares: $oddCount")
}
fun altSecuen(n: Int, stop: Int) {
    var number = n
    var stepCount = 0
    while (number != 1) {
        println(number)
        stepCount++
        if (number == stop) {
            println("Se detuvo en el movimiento $stepCount")
            break
        }
        number = if (number % 2 == 0) number / 2 else 3 * number + 1
    }
    println(number)
}