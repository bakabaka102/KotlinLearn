package basenormal

import helpers.printf
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class DivideZero(private val content: String) : RuntimeException() {

    override val message: String
        get() = content

}

interface ICalculator {

    //@Throws(DivideZero::class)
    fun divide(double: Double, number: Int = 0): Double
}

class ElectronicCalculator : ICalculator {

    override fun divide(double: Double, number: Int): Double {
        if (number == 0) {
            throw DivideZero("Can not divide 0")
        }
        return double.div(number)
    }
}

private fun testDouble() {
    val cal = ElectronicCalculator()
    val double: Double = 12.toDouble()
    val number = 4
    printf("Outside coroutine scope")
    printf(cal.divide(double))
    CoroutineScope(Dispatchers.IO).launch {
        printf("Inside coroutineScope")
        printf(cal.divide(0.0))
    }
}

fun testString() {
    val stringA = "123?qwe?rty"
    val stringB = "This is string B"
    printf("1 --- take 3: ${stringA.take(3)}")
    printf("2 --- takeLast 3: ${stringA.takeLast(3)}")
    printf("3 --- subStringAfter: ${stringA.substringAfter("?")} ---- ${stringB.substringAfter("?")}")
    printf("4 --- subStringAfterLast: ${stringA.substringAfterLast("?")} ---- ${stringB.substringAfterLast("?")}")
    printf("5 --- subString${stringA.split("?")} ---- ${stringB.split("?")}")
    printf("6 --- substring: ${stringA.substring(5)}")
}

suspend fun testYield() {
    val listData = arrayOf(yield(), yield())
}

fun testByteArray() {
    val bytes = byteArrayOf(1, -1, Byte.MIN_VALUE, Byte.MAX_VALUE)
    val str = String(bytes)
    val byteArray = byteArrayOf(0x48, 101, 108, 108, 111)
    val string = String(byteArray)
    printf(string)
    printf(str)
    printf("Value int: ${Integer(0x48)}")
    printf("Value toInt: ${0x48.toInt()}")
    printf(byteArray.toString(Charsets.UTF_8))
}

fun testChannel() {
    runBlocking {
        /*val channel = Channel<Int>()
        launch {
            // this might be heavy CPU-consuming computation or async logic,
            // we'll just send five squares
            for (x in 1..5) channel.send(x * x)
        }
        // here we print five received integers:
        repeat(5) { printf(channel.receive()) }
        printf("Done!")*/

        val channel = Channel<Int>()
        launch {
            for (x in 1..5) channel.send(x * x)
            channel.close() // we're done sending
        }
        // here we print received values using `for` loop (until the channel is closed)
        for (y in channel) printf(y)
        printf("Done!")
    }
}

fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}


fun main() {
    //testDouble()
    //testString()
    //testByteArray()
    //testChannel()
    /*runBlocking {
        val squares = produceSquares()
        squares.consumeEach { printf(it) }
        printf("Done!")
    }*/

    /*val listPositive: List<Float> = listOf(5f, 8f, 3f, 11f, 2f, 4f, 3f)
    val listNegative: List<Float> = listOf(-5f, -6f, -3f, -1f, -2f, -8f, -3f)

    val maxPositive = listPositive.max().also {
        printf("Max positive: $it")
    }
    val minPositive = listPositive.min().also {
        printf("Min positive: $it")
    }
    val maxNegative = listNegative.max().also {
        //printf("Max negative: $it")
    }
    val minNegative = listNegative.min().also {
        //printf("Min negative: $it")
    }

    val number = -12 // Ví dụ số
    val result = nextMultipleOfNumber(number)
    printf("Giá trị tiếp theo $number chia hết cho 5 là: $result")

    val parts = 5
    val start = previousMultipleOfNumber(value = minPositive.toInt(), number = parts).also {
        printf("Giá trị start - $it")
    }
    val end = nextMultipleOfNumber(value = maxPositive.toInt(), number = parts).also {
        printf("Giá trị end - $it")
    }
    val dividedRange = divideRangeIntoEqualParts(start = start, end = end, parts = parts)

    printf("Khoảng giá trị chia thành các phần bằng nhau: $dividedRange")*/
    mapVsFlatMap()

}

private fun mapVsFlatMap() {
    val numbers = listOf(1, 2, 3, 4, 5)
    val squaredNumbers = numbers.map { it * it }
    printf("after map: \n$squaredNumbers") // Output: [1, 4, 9, 16, 25]

    val words = listOf("Kotlin", "Java", "Scala")
    val characters = words.flatMap { it.toList() }
    printf("after flatmap: \n$characters") // Output: [K, o, t, l, i, n, J, a, v, a, S, c, a, l, a]
}

private fun collect() {
    val numbersSequence = sequenceOf("four", "three", "two", "one")
    val numbers = listOf("one", "two", "three", "four")
    val numbersC = numbers.asSequence()
    val oddNumbers = generateSequence(1) { it + 2 }
    printf(oddNumbers.take(5).toList())
}

fun divideRangeIntoEqualParts(start: Int, end: Int, parts: Int): List<Int> {
    if (parts <= 0 || start >= end) return emptyList()

    val step = ((end - start) / (parts - 1)).also {
        printf("Step === $it")
    }
    return List(parts) { index ->
        start + index * step
    }
}

fun nextMultipleOfNumber(value: Int, number: Int = 4): Int {
    return if (value < 0) {
        ((value / number) - 1) * number
    } else {
        ((value / number) + 1) * number
    }
}

fun previousMultipleOfNumber(value: Int, number: Int = 4): Int {
    return if (value < 0) {
        ((value / number) + 1) * number
    } else {
        (value / number) * number
    }
}



