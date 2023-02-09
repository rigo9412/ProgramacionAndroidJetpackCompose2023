fun main() {
    var result = DiferenciaArreglos(
        arrayOf(3),
        arrayOf(1,2,3,4,0,0,"a","null"),
        arrayOf("2",1,0,"A",9,"NULL"))
    

    println(result.joinToString(" "))
}

//Esta funcion es sensible a tipos (2 != "2")
fun combineArrays(vararg arreglos : Array<out Any?>): Array<Any?> {
    if(arreglos.isEmpty()){
        return emptyArray()
    }

    var set = arreglos[0].toSet()
    for(i in 1 until arreglos.size){
        val temp = arreglos[i].toSet()
        set = (set - temp) + (temp - set)
    }
    return arrayOf(set)
}

//Esta funcion no es sensible a tipos (2 == "2")
fun DiferenciaArreglos(vararg arreglos: Array<out Any?>): Array<Any?> {
    if (arreglos.isEmpty()){
        return emptyArray()
    }

    var set = arreglos[0].map { it.toString() }.toSet()
    for (i in 1 until arreglos.size) {
        val temp = arreglos[i].map { it.toString() }.toSet()
        set = ((set - temp) + (temp - set)).toSet()
    }
    return set.toTypedArray()
}