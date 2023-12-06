package designpattern.creational_patterns.builder_pattern

data class Car(
    val carType: CarType? = CarType.CITY_CAR,
    val seats: Int? = 2,
    val engine: Engine? = Engine(volume = 10.toDouble(), mileage = 100.toDouble()),
    val transmission: Transmission? = Transmission.SINGLE_SPEED,
    private val tripComputer: TripComputer? = TripComputer(),
    var gpsNavigator: GPSNavigator? = GPSNavigator(),
    var fuel: Double? = 10.0,
) {
    init {
        tripComputer?.createCar(this)
    }
}

data class Manual(
    private val carType: CarType?,
    private val seats: Int?,
    private val engine: Engine?,
    private val transmission: Transmission?,
    private val tripComputer: TripComputer?,
    private val gpsNavigator: GPSNavigator?,
)