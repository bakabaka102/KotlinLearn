package hackerrank

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalTime
import kotlin.math.abs


class HackerRankFunc {
}

private val df = DecimalFormat("0.000000")

fun simpleArraySum(ar: Array<Int>): Int {
    return ar.sum()
}

fun aVeryBigSum(ar: Array<Long>): Long {
    return ar.sum()
}

fun compareTriplets(a: Array<Int>, b: Array<Int>): Array<Int> {
    val size = a.size
    var first = 0
    var second = 0
    for (i in 0 until size) {
        if (a[i] > b[i]) {
            first++
        } else if (a[i] < b[i]) {
            second++
        }
    }
    return arrayOf(first, second)
}

fun diagonalDifference(arr: Array<Array<Int>>): Int {
    // Initialize sums of diagonals
    var d1 = 0
    var d2 = 0
    val n = arr.size
    for (i in arr.indices) {
        for (j in arr.indices) {
            // finding sum of primary diagonal
            if (i == j) {
                d1 += arr[i][j]
            }
            // finding sum of secondary diagonal
            if (i == n - j - 1) {
                d2 += arr[i][j]
            }
        }
    }
    return abs(d1 - d2)
}

fun plusMinus(arr: Array<Int>): Unit {
    val df = DecimalFormat("0.000000")
    var positive = 0
    var negative = 0
    var zero = 0
    for (num in arr) {
        when {
            num > 0 -> {
                positive++
            }

            num < 0 -> {
                negative++
            }

            else -> {
                zero++
            }
        }
    }
    println(df.format(positive.toDouble() / arr.size))
    println(df.format(negative.toDouble() / arr.size))
    println(df.format(zero.toDouble() / arr.size))
}

fun staircase(n: Int): Unit {
    for (i in 1..n) {
        for (j in n - i downTo 1) {
            print(" ")
        }
        for (j in 0 until i) {
            print("#")
        }
        println()
    }
}

fun miniMaxSum(arr: Array<Int>): Unit {
    if (arr.isEmpty()) {
        return
    }
    val map = arr.map {
        it.toLong()
    }.sorted()
    /*var minimum = 0
    var maximum = 0
    println(arr.toList())*/
    val minimum = map.sum() - map[arr.size - 1]
    val maximum = map.sum() - map[0]
    println("$minimum $maximum")
}

fun birthdayCakeCandles(candles: Array<Int>): Int {
    /*val max = Collections.max(candles.toMutableList())
    return Collections.frequency(candles.toMutableList(), max)*/
    candles.sort()
    var sumCandles = 0
    for (i in 0 until candles.size) {
        if (candles[i] == candles[candles.size - 1]) {
            sumCandles++
        }
    }
    return sumCandles
}

fun timeConversion(s: String): String {
    val myObj = LocalTime.now()
    println(myObj)
    val sdf = SimpleDateFormat("HH:mm:ss")
    return sdf.format(s)
}

fun timeConversion(s: String, a: Int): String {
    //val parts = s.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val parts = s.split(":")
    var hours = parts[0].toInt()
    val minutes = parts[1].toInt()
    val seconds = parts[2].substring(0, 2).toInt()
    val ampm = parts[2].substring(2)
    if (ampm == "PM" && hours != 12) {
        hours += 12
    } else if (ampm == "AM" && hours == 12) {
        hours = 0
    }
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}


fun printExpression(res: Int, count: Int): Boolean {
    val FACTOR: Int = 101
    var N: Int = 0
    var A: IntArray = intArrayOf()
    var B: CharArray = charArrayOf()
    val mod = res % FACTOR

    if (count == N - 1) {
        return mod == 0
    }

    if (printExpression(mod * A[count + 1], count + 1)) {
        B[count] = '*'
        return true
    }

    if (printExpression(mod + A[count + 1], count + 1)) {
        B[count] = '+'
        return true
    }

    val auxRes = mod - A[count + 1]
    if (printExpression(auxRes, count + 1)) {
        B[count] = '-'
        return true
    }
    return false
}


fun gradingStudents(grades: Array<Int>): Array<Int> {
    val arr = mutableListOf<Int>()
    for (grade in grades) {
        var value: Int = grade
        if (grade >= 38) {
            if (grade % 5 >= 3) {
                value = grade / 5 * 5 + 5
            }
        }
        arr.add(value)
    }
    return arr.toTypedArray()
}


fun countApplesAndOranges(s: Int, t: Int, a: Int, b: Int, apples: Array<Int>, oranges: Array<Int>): Unit {


}

fun main() {
    val arDemo = arrayOf(73, 67, 38, 33)
    /*val a = arrayOf(1, 3, 5, 6)
    val b = arrayOf(1, 6, 5, 2)
    val result = compareTriplets(a, b)

    println(result.joinToString(" "))
    val arDemo = arrayOf(1, 2, 0, -2, 4, 6, 0, -4)
    val ar = arrayOf(
        arrayOf(11, 2, 4), arrayOf(4, 5, 6), arrayOf(10, 8, -12)
    )
    println(diagonalDifference(ar))
    plusMinus(arDemo)
    staircase(3)
    val arDemo = arrayOf(793810624, 895642170, 685903712, 623789054, 468592370)
    miniMaxSum(arDemo)
    println( birthdayCakeCandles(arDemo))
    println(timeConversion("07:05:45 PM", 1))
    val result = gradingStudents(arDemo)
    println(result.joinToString("\n"))*/

}