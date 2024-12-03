package hkrank

import helpers.printf
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.InputStreamReader


/*
class SolutionMain {

}*/

/*
 * Complete the 'fizzBuzz' function below.
 *
 * The function accepts INTEGER n as parameter.
 */

fun fizzBuzz(n: Int) {
    for (i in 1..n) {
        when {
            (i % 3 == 0 && i % 5 == 0) -> {
                printf("FizzBuzz")
            }

            (i % 3 == 0 /*&& i % 5 != 0*/) -> {
                printf("Fizz")
            }

            (/*i % 3 != 0 &&*/ i % 5 == 0) -> {
                printf("Buzz")
            }

            else -> {
                printf(i)
            }
        }
    }
}

// Counting Vowels in a given string
var strVowels = "aeiou"
fun countVowels(str: String): Int {
    var count = 0
    for (element in str) {
        if (strVowels.indexOf(element) >= 0) {
            count++
        }
    }
    return count
}

fun findSubstring(s: String, k: Int): String {
    val givenString: CharArray = s.toCharArray()
    var maxV = 0
    var resultString = ""
    // No Vowels found
    if (countVowels(s) == 0) return "Not found!"
    for (i in 0..givenString.size - k) {
        val myString = s.substring(i, i + k)
        val vowelsQuant = countVowels(myString)
        if (vowelsQuant > maxV) {
            maxV = vowelsQuant
            resultString = myString
        }
    }
    return resultString
}

fun <T> calKeyId(keyId: String, block: (String) -> T): T {
    try {
        return block(keyId)
    } catch (e: Exception) {
        throw Exception(e.message)
    }
}

fun main() {
    //val n = readln().trim().toInt()
    //fizzBuzz(15)
    //val s = readln()
    //val k = readln().trim().toInt()
    /* val result = findSubstring("qwdftr", 2)
     printf(result)*/

    /*val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(FileWriter(System.getenv("OUTPUT_PATH")))

    val s = bufferedReader.readLine()

    val k = bufferedReader.readLine().trim().toInt()

    val result: String = findSubstring(s, k)

    bufferedWriter.write(result)
    bufferedWriter.newLine()

    bufferedReader.close()
    bufferedWriter.close()*/
    calKeyId("123") {value ->
        printf(value)
    }
}