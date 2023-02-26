//fun simple(): List<Int> = listOf(1, 2, 3)

fun simple(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        Thread.sleep(500) // pretend we are computing it
        yield(i) // yield next value
    }
}

suspend fun simpleS(): List<Int> {
    //delay(1000) // pretend we are doing something asynchronous here
    return listOf(1, 2, 3)
}


fun main() {
    simple().forEach { value ->

        println(value)
    }
}


/*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}*/
