package designpattern.creational_patterns.prototype_pattern

import java.util.*


abstract class IShape {
    var x: Int = 0
    var y: Int = 0
    var color: String? = null

    constructor(iShape: IShape) {
        x = iShape.x
        y = iShape.y
        color = iShape.color
    }

    constructor() {

    }

    abstract fun clone(): IShape

    override fun equals(other: Any?): Boolean {
        if (other !is IShape) {
            return false
        }
        return other.x == x && other.y == y && Objects.equals(other.color, color)
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + (color?.hashCode() ?: 0)
        return result
    }
}