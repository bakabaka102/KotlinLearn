package helpers

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun printf(message: Any?) {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val formatted = current.format(formatter)
    println("[$formatted] $message")
}