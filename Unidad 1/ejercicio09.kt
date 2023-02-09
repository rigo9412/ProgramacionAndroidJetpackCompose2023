//Elaborar una función que aplica las siguientes reglas, ingresar un número entero N
//positivo y si es N es par lo dividimos y si es impar lo multiplicamos por 3 y le
//sumamos 1, esta función se deberá detener cuando llegue a 1.
//  a.  Modificar la función anterior y contar el número de pasos que le tomo llegar a
//      1 (uno), contar los números pares e impares.
//
//  b.  Modificar la función anterior para que reciba un segundo número entero y
//      buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,
//      detener el proceso e indicar en que paso lo encontró

fun main(){
    multiODiv(2,2)
}


fun multiODiv(valor:Int,tope:Int):String
{
    var i=0
    var pares=0
    var inpares=0
    //preguntamos si el residuo de una division en dos es 0, si es asi es par
    //sino es impar
    if(valor%2==0){
        pares++
        var valor=valor/2
        println("$valor\nPASO=$i,PARES=$pares,INPARES=$inpares\nNO SE ENCONTRO EN EL NUMERO $tope")
        return ("$valor\nPASO=$i,PARES=$pares,INPARES=$inpares\nNO SE ENCONTRO EN EL NUMERO $tope")
    }
    else {
//        println("Valor ($valor) es impar, multiplicado por tres es: ${valor * 3}")
        var nuevoValor=(valor*3)+1
        var seEncontroNumero="$nuevoValor\nPASO=$i,PARES=$pares,INPARES=$inpares\nSE ENCONTRO EN EL NUMERO $tope EN EL PASO:$i"
        while (nuevoValor!=1){

            //modificacion b, preguntamos si el otro numero se encuentra en los pasos
            if(nuevoValor==tope){
                println("$nuevoValor\nPASO=$i,PARES=$pares,INPARES=$inpares\nSE ENCONTRO EN EL NUMERO $tope EN EL PASO:$i")
                return ("$nuevoValor\nPASO=$i,PARES=$pares,INPARES=$inpares\nSE ENCONTRO EN EL NUMERO $tope EN EL PASO:$i")
            }

            //si es par dividimos, sino multiplicamos y le sumamos uno
            if(nuevoValor%2==0){
                nuevoValor=nuevoValor/2
                pares++
            }
            else {
                nuevoValor = (nuevoValor * 3) + 1;inpares++
            }
            i++
        }
        //imprimimos lo ultimo sin haber encontrado el numero
        println("$nuevoValor\nPASO=$i,PARES=$pares,INPARES=$inpares\nNO SE ENCONTRO EN EL NUMERO $tope")
        return ("$nuevoValor\nPASO=$i,PARES=$pares,INPARES=$inpares\nNO SE ENCONTRO EN EL NUMERO $tope")

    }
}