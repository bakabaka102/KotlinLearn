package designpattern.builder_pattern

interface IBuilder {
    fun setCarType(type: CarType?)
    fun setSeats(seats: Int)
    fun setEngine(engine: Engine?)
    fun setTransmission(transmission: Transmission?)
    fun setTripComputer(tripComputer: TripComputer?)
    fun setGPSNavigator(gpsNavigator: GPSNavigator?)
}

data class GPSNavigator(val route: String = "221b, Baker Street, London  to Scotland Yard, 8-10 Broadway, London")

class TripComputer {

    private var car: Car? = null

    fun createCar(car: Car) {
        this.car = car
    }

    fun statusRun(): String {
        return if (car?.engine?.isStarted() == true) {
            "Car is started"
        } else {
            "Car isn't started"
        }
    }

}

class Engine(val volume: Double, var mileage: Double) {

    private var started = false

    fun isStarted(): Boolean {
        return started
    }

    fun on() {
        started = true
    }

    fun off() {
        started = false
    }

    fun go(mileage: Double) {
        if (started) {
            this.mileage += mileage
        } else {
            System.err.println("Cannot go(), you must start engine first!")
        }
    }
}

enum class CarType {
    CITY_CAR, SPORTS_CAR, SUV
}

enum class Transmission {
    SINGLE_SPEED, MANUAL, AUTOMATIC, SEMI_AUTOMATIC
}

