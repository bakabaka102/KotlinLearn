package xmlhandle

import helpers.printf

fun main() {
    var x = 34
    val y = ++x // y bằng 35 và x cũng bằng 35 (x tăng rồi gán vào y)
    var z = 34
    val zz = z++ // y bằng 34 và x bằng 35 (y được gán, rồi x mới tăng)

    printf("Value x = $x, value y = $y, value z = $z, value zz = $zz")

    var a = 5
    val b = a++
    var i = 0
    printf("Value a = $a, value b = $b")
    val cars = arrayOf("Merc", "BMW", "Ford", "Opel")
    while (a > b) {
        printf(cars[2])
        i++
        if (i > 6) {
            break
        }
    }
}