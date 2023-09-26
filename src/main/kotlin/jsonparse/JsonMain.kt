package jsonparse

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.IOException
import java.io.InputStream


val customerObject = """
    {
       "firstName": "john",
       "lastName": "doe",
       "email": "john@gmail.com"
    },{
       "firstName": "john",
       "lastName": "doe",
       "email": "john@gmail.com"
    },
""".trimIndent()

data class Customer(
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null
)

data class Tutorial(
    val title: String,
    val author: String,
    val categories: List<String>
)

data class Person(val name: String, val age: Int, val messages: List<String>)

fun main() {
    val theGson = Gson()
    /*val file = FileReader("src/main/kotlin/jsonparse/file_json.json")
    val filePerson = FileReader("src/main/kotlin/jsonparse/file_json_.json")
    *//*val customer = theGson.fromJson(file, Array<Array<Customer>>::class.java).toList().toList().toString()
    val person = theGson.fromJson(filePerson, Array<Person>::class.java).toList()
    println("${person.size} --- $person")
    println(customer)*//*
//    println("${customer.size}")
//    for (i in customer) {
//        println("$i \n")
//    }
    val json3 = theGson.fromJson("src/main/kotlin/jsonparse/file_json_0.json", Person::class.java)
    println("$json3")*/

    val jsonList =
        """[{"title":"Kotlin Tutorial #1","author":"bezkoder","categories":["Kotlin, Basic"]},
			{"title":"Kotlin Tutorial #2","author":"bezkoder","categories":["Kotlin, Practice"]}]"""

    val gson = Gson()
    val arrayTutorialType = object : TypeToken<Array<Tutorial>>() {}.type

    val tutorials: Array<Tutorial> = gson.fromJson(jsonList, arrayTutorialType)
    tutorials.forEachIndexed { idx, tut -> println("> Item ${idx}:\n${tut}") }

    val arrayWithTitle = object : TypeToken<Array<Person>>() {}.type
    val json = (readJSONFromFile("src/main/kotlin/jsonparse/file_json_0.json") ?: "").also {
        //print(it)
    }
    Json.decodeFromString<DataJson>(json).also {
        print("Data json parse: ${it}")
    }
    /*val json3 : Array<Person> = gson.fromJson(json, arrayWithTitle)
    println("${json3.toList()}")*/
}


fun readJSONFromFile(path: String): String? {
    return try {
        val inputStream: InputStream = FileInputStream(path)
        inputStream.bufferedReader().use { it.readText() }
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }


    /*var json: String? = null
    try {
        val inputStream: InputStream = getAssets().open("events.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, charset("UTF-8"))
    } catch (e: IOException) {
        e.printStackTrace()
    }*/
}