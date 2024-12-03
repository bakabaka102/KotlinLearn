package udacity

import helpers.printf
import java.util.*
import kotlin.random.Random

class UdacityTest {

    var status = false
}

fun main(arg: Array<String>) {
    val u1 = UdacityTest()
    u1.status = false
    val u2 = UdacityTest()
    u2.status = true
    printf("Status: {$u1.status}  --- Argument: ${arg[0]}")
    dayOfWeek()
    printf("Get a random day: ${randomDay()}")
}

fun dayOfWeek() {
    printf("What day is it today?")
    val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    printf(
        "Today is: ${
            when (day) {
                1 -> "Sunday"
                2 -> "Monday"
                3 -> "Tuesday"
                4 -> "Wednesday"
                5 -> "Thursday"
                6 -> "Friday"
                7 -> "Saturday"
                else -> "Time has stopped"
            }
        }"
    )
}

fun randomDay(): String {
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val day = days[Random.nextInt(days.size)]
    return day
}

class Contact(var a: String)