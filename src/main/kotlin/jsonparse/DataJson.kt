package jsonparse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataJson(
    @SerialName("cities")
    val cities: List<City>,
    @SerialName("states")
    val states: List<State>
)

@Serializable
data class City(
    @SerialName("age")
    val age: Int,
    @SerialName("messages")
    val messages: List<String>,
    @SerialName("name")
    val name: String
)

@Serializable
data class State(
    @SerialName("age")
    val age: String,
    @SerialName("messages")
    val messages: List<String>,
    @SerialName("name")
    val name: String
)