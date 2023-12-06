package designpattern.structure_patterns.adapter_pattern

import kotlin.math.pow
import kotlin.math.sqrt

open class RoundPeg(rd: Double?) {

    private var radius = 0.0

    init {
        if (rd != null) {
            radius = rd
        }
    }
    open fun getRadius(): Double {
        return radius
    }
}

class RoundHole(private var radius: Double = 0.0) {

    fun fits(peg: RoundPeg): Boolean = radius >= peg.getRadius()
}

class SquarePeg(var width: Double = 0.0) {
    fun getSquare(): Double = width.pow(2.0)
}

class SquarePegAdapter(var peg: SquarePeg? = null) : RoundPeg(peg?.getSquare()) {

    override fun getRadius(): Double {
        // Calculate a minimum circle radius, which can fit this peg.
        return sqrt(((peg?.width?.div(2))?.pow(2.0) ?: 0.0) * 2)
    }
}

fun main() {
    // Round fits round, no surprise.
    val hole = RoundHole(5.0)
    val rpeg = RoundPeg(5.0)
    if (hole.fits(rpeg)) {
        println("Round peg r5 fits round hole r5.")
    }
    val smallSqPeg = SquarePeg(2.0)
    val largeSqPeg = SquarePeg(20.0)
    // hole.fits(smallSqPeg); // Won't compile.

    // Adapter solves the problem.
    val smallSqPegAdapter = SquarePegAdapter(smallSqPeg)
    val largeSqPegAdapter = SquarePegAdapter(largeSqPeg)
    if (hole.fits(smallSqPegAdapter)) {
        println("Square peg w2 fits round hole r5.")
    }
    if (!hole.fits(largeSqPegAdapter)) {
        println("Square peg w20 does not fit into round hole r5.")
    }
}