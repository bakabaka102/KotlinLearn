package helpers

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Utils {

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
}

/*
fun printf(message: Any?) {
    val currentTime: String = LocalTime.now().format(Utils.formatter)
    println("[$currentTime]: $message")
}*/
