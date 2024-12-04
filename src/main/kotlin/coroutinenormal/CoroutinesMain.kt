package coroutinenormal

import helpers.printf
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.time.ExperimentalTime

fun main(): Unit = runBlocking {
    launch {
        /*testSharedFlow()
        testStateFlow()*/

        //testCoroutine()
        //testChannels()
        testAsyncAwait()
        //testDelayAndSleep()
        //normalRun()
        //testJoin()
        //testLightWeight()
    }
    printf("Hello, this is main function ----------------->>>.")
}

/*fun main() {
    CoroutineScope(Dispatchers.IO).launch {
        printf("Before measure")
        val time = measureTimeMillis {
            val one = async { value() }
            val two = async { value(2000L, 20) }
            printf("The answer is ${one.await() + two.await()}")
        }
        printf("Completed in $time ms")
    }
    Thread.sleep(3000)
}*/

suspend fun testJoin() {
    val job = CoroutineScope(Dispatchers.IO).launch {
        delay(1000)
        printf("World")
    }
    printf("Hello,")
    job.join()
    printf("All done")
}

fun testLightWeight() {
    measureTimeMillis {
        runBlocking {
            repeat(100_000) {
                launch {
                    delay(1000)
                    printf(it)
                }
            }
        }
    }.also {
        printf("Completed in $it ms")
    }
}

suspend fun loadData(timeDelay: Long = 1000L, value: Int = 100): Int {
    printf("Loading...")
    delay(timeDelay)
    printf("Loaded.!")
    return value
}


private fun testDelayAndSleep() {
    CoroutineScope(Dispatchers.Default).launch {
//        delay(1000L) // delay 1s nhưng ko làm blocking app
        Thread.sleep(1000L) // block app 1s
        printf("World,") // print từ World ra sau khi hết delay
    }
    printf("Hello,") // main thread vẫn tiếp tục chạy xuống dòng code này trong khi coroutine vẫn đang bị delay 10s
    Thread.sleep(2000L) // block main thread 2s
    //delay(2000L) // Delay 2s
    printf("Kotlin")
}

private suspend fun testAsyncAwait() {
    printf("---Async Await---")
    coroutineScope {
        measureTimeMillis {
            val deferreds: List<Deferred<Int>> = (1..3).map {
                async(Dispatchers.IO) {
                    delay(1000L * it)
                    printf("Loading $it")
                    it
                }
            }
            deferreds.awaitAll().sum().also { printf("Sum of list: $it") }
        }.also {
            printf("Async/Await list: Completed in $it ms")
        }
        measureTimeMillis {
            val one: Deferred<Int> = async { loadData() }
            val two: Deferred<Int> = async { loadData(2000L, 20) }
            printf("The answer is ${one.await() + two.await()}")
        }.also { time ->
            printf("Completed in $time ms")
        }
    }
    printf("---Normal flow---")
    measureTimeMillis {
        loadData() + loadData(2000L, 20).also {
            printf("The answer is $it")
        }
    }.also {
        printf("Completed in $it ms")
    }
}

private suspend fun testStateFlow(): Nothing {
    coroutineScope {
        val stateFlow = MutableStateFlow(0)
        launch {
            repeat(5) {
                delay(1000)
                printf("Sent: $it")
                stateFlow.value = it
            }
        }
        stateFlow.collect { value ->
            printf("Received: $value")
            if (value == 4) {
                return@collect
            }
        }
    }
}

private suspend fun testSharedFlow(): Nothing {
    coroutineScope {
        val sharedFlow = MutableSharedFlow<Int>(replay = 1)
        launch {
            repeat(5) {
                delay(1000)
                printf("Emitted: $it")
                sharedFlow.emit(it)
            }
        }
        sharedFlow.collect { value -> printf("Received: $value") }
    }
}

@OptIn(ExperimentalTime::class)
suspend fun normalRun() {
    measureTime {
        printf("Before Sleep")
        //Thread.sleep(1000L)
        delay(1000L)
        printf("After Sleep")
    }.also {
        printf("Take time: $it")
    }
}

private suspend fun testChannels() = coroutineScope {
    val channel = Channel<Int>()
    launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5) channel.send(x * x)
    }
    // here we print five received integers:
    repeat(5) {
        printf(channel.receive())
    }
    printf("Done!")

    val channel2 = Channel<Int>()
    launch {
        for (x in 1..5) channel2.send(x * x)
        channel2.close() // we're done sending
    }
    // here we print received values using `for` loop (until the channel is closed)
    for (y in channel2) printf(y)
    printf("Done!")
}

private suspend fun testCoroutine() {
    function1()
    function2()
    function3()
}

suspend fun function1() {
    printf("Function 1")
    delay(1000)
}

suspend fun function2() {
    //delay(1000)
    printf("Function 2")
}

suspend fun function3() {
    printf("Function 3")
    delay(1000)
}