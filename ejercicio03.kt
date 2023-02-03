//Elaborar una función que reciba un texto y cuente el número de letras que contiene.

package Adicional

import java.util.*

class ConteoLetras {
    fun contarLetras(frase: String): Int {
        val letras = charArrayOf(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        )
        var numeroLetras = 0
        val fraseMinusculas = frase.lowercase(Locale.getDefault())
        for (i in 0 until fraseMinusculas.length) {
            val letra = fraseMinusculas[i]
            if (letra != ' ') {
                for (j in letras.indices) {
                    if (letra == letras[j]) {
                        numeroLetras++
                        break
                    }
                }
            }
        }
        return numeroLetras
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lector = Scanner(System.`in`)
            val frase: String
            println("Ingrese palabra o frase: ")
            frase = lector.nextLine()
            val contador = ConteoLetras()
            println(contador.contarLetras(frase))
        }
    }
}







    

