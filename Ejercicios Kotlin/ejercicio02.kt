

// Elaborar una función que convierta un texto de camelCase
// Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"

object LowerCamel {
    fun camelCase(s: String): String {
        var ctr = 0
        val n = s.length
        val ch = s.toCharArray()
        var c = 0
        for (i in 0 until n) {
            if (i == 0)
                ch[i] = ch[i].lowercaseChar()
            if (ch[i] == ' ') {
                ctr++
                ch[i + 1] = ch[i + 1].uppercaseChar()
                continue
            } else ch[c++] = ch[i]
        }
        return String(ch, 0, n - ctr)
    }
    @JvmStatic
    fun main(args: Array<String>) {
        val str2 = "Hola como estas."
        println(camelCase(str2))
    }
}