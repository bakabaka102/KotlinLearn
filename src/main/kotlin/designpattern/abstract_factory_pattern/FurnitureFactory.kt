package designpattern.abstract_factory_pattern

enum class MaterialType {
    PLASTIC, WOOD,
}

interface Table {
    fun create()
}

interface Chair {
    fun create()
}

class PlasticFactory : FurnitureAbstractFactory() {
    override fun createChair(): Chair = PlasticChair()

    override fun createTable(): Table = PlasticTable()

}

class WoodFactory : FurnitureAbstractFactory() {
    override fun createChair(): Chair = WoodChair()

    override fun createTable(): Table = WoodTable()

}

abstract class FurnitureAbstractFactory {
    abstract fun createChair(): Chair
    abstract fun createTable(): Table
}

class PlasticChair : Chair {
    override fun create() {
        println("Create PlasticChair")
    }
}

class WoodChair : Chair {
    override fun create() {
        println("Create WoodChair")
    }
}

class WoodTable : Table {
    override fun create() {
        println("Create WoodTable")
    }
}

class PlasticTable : Table {
    override fun create() {
        println("Create PlasticTable")
    }
}

object FurnitureFactory {

    fun getFactory(materialType: MaterialType): FurnitureAbstractFactory {
        return when (materialType) {
            MaterialType.PLASTIC -> PlasticFactory()
            MaterialType.WOOD -> WoodFactory()
        }
    }
}

fun main() {
    val plasticFactory = FurnitureFactory.getFactory(MaterialType.PLASTIC)
    val chairPlastic: Chair = plasticFactory.createChair()
    chairPlastic.create()
    val tablePlastic: Table = plasticFactory.createTable()
    tablePlastic.create()
}