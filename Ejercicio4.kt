fun main()
{
    print(Descuento(10,100.0))
}

fun Descuento(desc : Int ,subt:Double) : Double
{
    var des: Double = 0.0
    if(desc >= 1) des = desc * subt/ 100.0
    return subt - des
}