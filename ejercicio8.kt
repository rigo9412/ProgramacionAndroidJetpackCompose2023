// fun mergeArrays(): IntArray {
//     var arr = intArrayOf(1,2,3)
//     var arr2 = intArrayOf(3,2,1)
//     var arr3 = intArrayOf(4,5,6)

//     for (i in arr) {
//         if(!arr2.contains(i)){
//             val result = arr2.toMutableList()
//             result.add(arr2.size, i)
//             arr2 = result.toIntArray()
//         }
//     }

//     for (j in arr2) {
//     if(!arr3.contains(j)){
//         val result = arr3.toMutableList()
//         result.add(arr3.size, j)
//         arr3 = result.toIntArray()
//         }
//     }
//     return arr3
// }

// fun main(){  
//     println(mergeArrays(arrayOf(3), arrayOf(1,2,3,4,0,0,"a","null"), arrayOf("2",1,0,"A",9,"NULL")).joinToString())
    
// }


// fun mergeArrays(vararg array: Array<Any>):Array<Any> {
//     var arr = mutableListOf<Any>()
//     var difernetes = mutableListOf<Any>()

//     for (i in array) difernetes.addAll(i)

//     difernetes.groupBy{it.toString()}.forEach{ 
//             if(it.value.count()==1) lista.add(it.key) 
//     } 
    
    
//     return arr.toTypedArray()
// }

fun main() {
    val arr1 = arrayOf(1, 2, 3, 4, 5)
    val arr2 = arrayOf(4, 5, 6, 7, 8)
    val arr3 = arrayOf(7, 8, 9, 10, 11)

    val differences = difference(*arrayOf(arr1, arr2, arr3))
    println(differences.joinToString(", "))
}

fun difference(vararg arrays: Array<Int>): Array<Int> {
    val set = mutableSetOf<Int>()
    for (array in arrays) {
        for (element in array) {
            set.add(element)
        }
    }

    val result = mutableSetOf<Int>()
    for (array in arrays) {
        for (element in set) {
            if (!array.contains(element)) {
                result.add(element)
            }
        }
    }

    return result.toTypedArray()
}