fun main()
{
    var array1 : Array<Any> = arrayOf(1,2,3,4,0,0,"a","null").toMutableList().distinct().toTypedArray()
    var array2 : Array<Any> = arrayOf("2",1,0,"A",9,"NULL").toMutableList().distinct().toTypedArray()
    // 4,a,null,A,9,NULL resultado
    var array = sacarDiferencia(array1, array2, arrayOf(3))
    for(c in array)
    {
        print("$c ")
    }
}

fun sacarDiferencia(vararg array: Array<Any>):Array<Any>
{
    var lista : MutableList<String> = mutableListOf()
    for (c in array)
    {
        for(s in c)
        {
            var existe : String? = lista.firstOrNull { it == s.toString() }
            if (existe!=null)
            {
                //var index = lista.indexOf(existe)
                lista.remove(existe)
            }
            else{
                lista.add(s.toString())
            }
            
        }
    }
    //lista = lista.toSet().toMutableList()
    return lista.toTypedArray()
}

