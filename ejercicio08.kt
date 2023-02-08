//PROFE
fun combineArrays(vararg data: Array<Any>): Array<Any>{
    val resulArray = mutableListOf<Any>()
    val combineArrays = mutableListOf<Any>()
    for (item in data) combineArrays.addAll(item)

    combineArrays.groupBy { it.toString()}.forEach {it.Map.Entry<Stirng.List<Any>>
        if (it.value.count() == 1) resultArray.add(it.key)
    }

    return resulArray.toTypedArray()
}