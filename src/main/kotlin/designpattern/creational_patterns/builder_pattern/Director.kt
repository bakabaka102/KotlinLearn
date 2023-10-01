package designpattern.creational_patterns.builder_pattern

fun IBuilder.constructSportsCar() {
    this.setCarType(CarType.SPORTS_CAR)
    this.setSeats(2)
    this.setEngine(Engine(3.0, 0.0))
    this.setTransmission(Transmission.SEMI_AUTOMATIC)
    this.setTripComputer(TripComputer())
    this.setGPSNavigator(GPSNavigator())
}

fun IBuilder.constructCityCar() {
    this.setCarType(CarType.CITY_CAR)
    this.setSeats(2)
    this.setEngine(Engine(1.2, 0.0))
    this.setTransmission(Transmission.AUTOMATIC)
    this.setTripComputer(TripComputer())
    this.setGPSNavigator(GPSNavigator())
}

fun IBuilder.constructSUV() {
    this.setCarType(CarType.SUV)
    this.setSeats(4)
    this.setEngine(Engine(2.5, 0.0))
    this.setTransmission(Transmission.MANUAL)
    this.setGPSNavigator(GPSNavigator())
}

fun main() {
    val manualBuilder = CarManualBuilder()
    // Director may know several building recipes.
    manualBuilder.constructSportsCar()
    val carManual = manualBuilder.getResult()
    println("Car manual built:  $carManual")

    val carBuilder = CarBuilder()
    // Director may know several building recipes.
    carBuilder.constructSportsCar()
    val car = carBuilder.getResult()
    println("Car built:  $car")
}