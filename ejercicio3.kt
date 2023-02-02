fun main() {
    counterChar("hohd032h0923he023heh2093he0923heh902h0hola")
}

fun counterChar(value: String): Unit {
    var res = value.filter{it.isLetter()}.length
    print(res)
}