import Direction.Directions
import kotlinx.coroutines.delay
import rx_learn.justOperator

//fun simple(): List<Int> = listOf(1, 2, 3)

fun simple(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        Thread.sleep(500) // pretend we are computing it
        yield(i) // yield next value
    }
}

suspend fun simpleS(): List<Int> {
    delay(1000) // pretend we are doing something asynchronous here
    return listOf(1, 2, 3)
}


fun main() {
    /*simple().forEach { value ->
        println(value)
    }

    println(Directions.SOUTH)

    Direction.Directions.values().forEach {
        println(it)
    }*/
    //justOperator()
    //mapOperator()
    flatMapOperator()
}

fun flatMapOperator() {
    val cars = listOf(
        MotorVehicle("Swift", 2016, "Maruti"),
        MotorVehicle("Altroz", 2020, "Tata"),
        MotorVehicle("Verna", 2019, "Hyundai")
    )
    val bikes = listOf(
        MotorVehicle("R-15", 2018, "Yamaha"),
        MotorVehicle("Gixxer", 2017, "Suzuki")
    )

    val allVehicles = mutableListOf<MotorVehicle>()
    allVehicles.addAll(cars)
    allVehicles.addAll(bikes)

    println(allVehicles)

    val vehicles = listOf(cars, bikes)
    println(vehicles)
    val allVehiclesFlatten = vehicles.flatten()
    val allVehiclesFlatMap = vehicles.flatMap { vehicles ->
        vehicles
    }
    println(allVehiclesFlatMap)
}

private fun mapOperator() {
    val numbers = listOf(1, 2, 3, 4, 5)
    //val squaredNumbers = mutableListOf<Int>()
    // numbers.forEach {
    //        squaredNumbers.add(it * it)
    //    }
    val squaredNumbers = numbers.map {
        it * it
    }
    print(squaredNumbers)
}

data class MotorVehicle(
    val name: String,
    val model: Int? = null,
    val manufacturer: String? = null,
) {
    override fun toString(): String {
        return "MotorVehicle(name='$name')"
    }
}