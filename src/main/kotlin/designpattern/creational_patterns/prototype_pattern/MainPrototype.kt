package designpattern.creational_patterns.prototype_pattern

fun main() {
    val shapes: MutableList<IShape> = ArrayList()
    val circle = Circle(radius = 15)
    circle.x = 10
    circle.y = 20
    circle.color = "red"
    shapes.add(circle)

    val anotherCircle = circle.clone()
    shapes.add(anotherCircle)

    val rectangle = Rectangle(width = 10, height = 20)
    rectangle.color = "blue"
    shapes.add(rectangle)
    cloneAndCompare(shapes)
}

fun cloneAndCompare(shapes: MutableList<IShape>) {
    val shapesCopy = mutableListOf<IShape>()
    for (shape in shapes) {
        shapesCopy.add(shape.clone())
    }
    for (copy in shapesCopy) {
        println("Gia tri cua ban sao: $copy")
    }
    for (i in 0 until shapes.size) {
        println("Index = $i ------ shapes[i]: ${shapes[i]}  -----    shapesCopy: ${shapesCopy[i]}")
        if (shapes[i] != shapesCopy[i]) {
            println("$i: Shapes are different objects (yay!)")
            if (shapes[i] == shapesCopy[i]) {
                println("$i: And they are identical (yay!)")
            } else {
                println("$i: But they are not identical (booo!)")
            }
        } else {
            println("$i: Shape objects are the same (booo!)")
        }
    }
}
