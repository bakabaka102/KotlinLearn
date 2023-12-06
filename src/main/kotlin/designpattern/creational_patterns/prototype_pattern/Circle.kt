package designpattern.creational_patterns.prototype_pattern

data class Circle(var radius: Int?) : IShape() {

    override fun clone(): IShape {
        return Circle(radius)
    }

    override fun equals(other: Any?): Boolean {
        return if (other !is Circle || !super.equals(other)) {
            false
        } else {
            other.radius == radius
        }
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (radius ?: 0)
        return result
    }
}

data class Rectangle(var width: Int?, var height: Int?) : IShape() {
    override fun clone(): IShape {
        return Rectangle(width, height)
    }

    override fun equals(other: Any?): Boolean {
        return if (other !is Rectangle || !super.equals(other)) {
            false
        } else {
            other.width == width && other.height == height
        }
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (width ?: 0)
        result = 31 * result + (height ?: 0)
        return result
    }
}