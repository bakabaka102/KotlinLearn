package designpattern.structure_patterns.proxy_pattern

interface ICalculator {

    fun add(first: Double, second: Double): Double

    fun div(first: Double, second: Double): Double
}

class RealCalculator : ICalculator {
    override fun add(first: Double, second: Double): Double {
        return first + second
    }

    override fun div(first: Double, second: Double): Double {
        return first.div(second)
    }

}

class ProxyCalculator : ICalculator {

    private var realCalculator: RealCalculator = RealCalculator()

    override fun add(first: Double, second: Double): Double {
        if (first / 2 + second / 2 >= Double.MAX_VALUE / 2) {
            throw RuntimeException("Gia tri qua lon")
        }
        if (first / 2 + second / 2 <= Double.MIN_VALUE / 2) {
            throw RuntimeException("Gia tri qua nho")
        }
        return realCalculator.add(first, second)
    }

    override fun div(first: Double, second: Double): Double {
        if (second == 0.0) {
            throw RuntimeException("So chia bang 0")
        }
        return realCalculator.div(first, second)
    }
}

fun main() {
    val iCalculator = RealCalculator()
    println("MIN_DOUBLE: ${Double.MIN_VALUE}")
    println("MAX_DOUBLE: ${Double.MAX_VALUE}")
    println("Cong: ${iCalculator.add(100.0, Double.MAX_VALUE)}")
    val proxy = ProxyCalculator()
    try {
        //println("Cong: ${proxy.add(100.0, Double.MAX_VALUE)}")
        println("Cong: ${proxy.add(9000000000000000000.0, Double.MIN_VALUE)}")
        println("Chia: ${proxy.div(100.0, 0.0)}")
    } catch (ex: RuntimeException) {
        println("Co loi do: ${ex.message}")
    }
}