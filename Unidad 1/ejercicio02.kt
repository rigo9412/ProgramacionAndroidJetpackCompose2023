fun main(){
    convertir("Hola como estas")
}

fun convertir(texto: String){
    if(texto.contains(" "))
    {
        //print("tiene espacios en blanco")
        val letras: CharArray = texto.toCharArray()
        var n = texto.length - 1
        var i = 0
        var str : String
        while (i<=n)
        {
            if(i==0)
            {
                str = letras[i].toString()
                print(str.lowercase())
                i++
                continue
            }
            else if (letras[i] == ' ')
            {
                i++
                str = letras[i].toString()
                print(str.uppercase())
                i++
                continue
            }
            print(letras[i])
            i++            
        }
    }
    else
    {
        print("no tiene espacios en blanco")
    }
}