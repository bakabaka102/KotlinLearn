package retrofit

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class RandomObject(
    @SerialName("categories")
    var categories: ArrayList<String> = arrayListOf(),
    @SerialName("created_at")
    var createdAt: String? = null,
    @SerialName("icon_url")
    var iconUrl: String? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("updated_at")
    var updatedAt: String? = null,
    @SerialName("url")
    var url: String? = null,
    @SerialName("value")
    var value: String? = null,
)

@Serializable
data class Note(
    @SerialName("userId")
    var userId: Int? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("title")
    var title: String? = null,
    @SerialName("completed")
    var completed: Boolean? = null
)

@Serializable
data class User(
    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("username") var username: String? = null,
    @SerialName("email") var email: String? = null,
    @SerialName("address") var address: Address? = Address(),
    @SerialName("phone") var phone: String? = null,
    @SerialName("website") var website: String? = null,
    @SerialName("company") var company: Company? = Company()
)

@Serializable
data class Geo(
    @SerialName("lat") var lat: String? = null,
    @SerialName("lng") var lng: String? = null,
)

@Serializable
data class Address(
    @SerialName("street") var street: String? = null,
    @SerialName("suite") var suite: String? = null,
    @SerialName("city") var city: String? = null,
    @SerialName("zipcode") var zipcode: String? = null,
    @SerialName("geo") var geo: Geo? = Geo(),
)

@Serializable
data class Company(
    @SerialName("name") var name: String? = null,
    @SerialName("catchPhrase") var catchPhrase: String? = null,
    @SerialName("bs") var bs: String? = null,
)