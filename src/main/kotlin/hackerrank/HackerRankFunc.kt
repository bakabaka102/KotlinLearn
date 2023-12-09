package hackerrank

import java.text.DecimalFormat
import kotlin.math.abs


class HackerRankFunc {
}

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
private val df = DecimalFormat("0.000000")

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

fun main() {
    /*val a = arrayOf(1, 3, 5, 6)
    val b = arrayOf(1, 6, 5, 2)
    val result = compareTriplets(a, b)

    println(result.joinToString(" "))*/
    val arDemo = arrayOf(1, 2, 0, -2, 4, 6, 0, -4)
    val ar = arrayOf(
        arrayOf(11, 2, 4), arrayOf(4, 5, 6), arrayOf(10, 8, -12)
    )
    println(diagonalDifference(ar))
    plusMinus(arDemo)
}