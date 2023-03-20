import kotlin.math.sqrt

fun main() {
    val operacion = "((6 * 3) + 4) / 2"
    println(calcularOperacion(operacion))
}

fun calcularOperacion(operacion: String): Double {
    val res = 0.0
    try {
        return eval(operacion)
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
    return res
}

fun eval(str: String): Double {
    return object : Any() {
        var pos = -1
        var ch = ' '
        fun nextChar() {
            ch = if (++pos < str.length) str[pos] else '\u0000'
        }
        fun eat(charToEat: Char): Boolean {
            while (ch == ' ') nextChar()
            if (ch == charToEat) {
                nextChar()
                return true
            }
            return false
        }
        fun parseAtom(): Double {
            eat('(')
            val x = if (eat('-')) -parseAtom() else {
                var n = 0.0
                while (ch in '0'..'9') {
                    n = n * 10 + (ch - '0')
                    nextChar()
                }
                if (ch == '.') {
                    nextChar()
                    var f = 1.0
                    while (ch in '0'..'9') {
                        f /= 10
                        n += (ch - '0') * f
                        nextChar()
                    }
                }
                n
            }
            eat(')')
            return if (eat('^')) Math.pow(x, parseAtom()) else x
        }
        fun parseFactor(): Double {
            var x = parseAtom()
            while (true) {
                when {
                    eat('*') -> x *= parseAtom()
                    eat('/') -> x /= parseAtom()
                    eat('%') -> x %= parseAtom()
                    else -> return x
                }
            }
        }
        fun parse(): Double {
            nextChar()
            var x = parseFactor()
            while (true) {
                when {
                    eat('+') -> x += parseFactor()
                    eat('-') -> x -= parseFactor()
                    else -> return x
                }
            }
        }
    }.parse()
}
