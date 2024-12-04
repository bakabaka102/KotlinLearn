package ln

import helpers.printf

data class Car(val name: String, val year: String)

class MainLn {
    //private val greeting: String? = "Hello, Hi, Ni-hao!"
    private val greeting: String? = null
    private val nameClass = MainLn::class.java.name
    private val porsche = Car("Porsche", "2018")
    private val mclawrence = Car("MCLawrence", "2008")

    override fun toString(): String {
        return nameClass
    }

    fun letFunction(): String? {
        //val greeting = "Hello, Hi, Ni-hao!"
        return greeting?.let {
            printf(this)
            printf(it)
            "Function with let.!"
        }
    }

    fun runFunction(): String? {
        return greeting?.run {
            printf(this)
            //printf(it)
            "Function with run.!"
        }
    }

    fun applyFunction(): String? {
        return greeting?.apply {
            printf(this)
            //printf(it)
            "Function with apply.!"
        }
    }

    fun alsoFunction(): String? {
        return greeting?.also {
            printf(it)
            printf(this)
            "Function with also.!"
        }
    }

    fun main() {
        printf("Function main in ln package.")
    }
}

fun main() {
    printf(MainLn().letFunction())
    printf("New func-------------------------")
    printf(MainLn().runFunction())
    printf("New func-------------------------")
    printf(MainLn().applyFunction())
    printf("New func-------------------------")
    printf(MainLn().alsoFunction())
}