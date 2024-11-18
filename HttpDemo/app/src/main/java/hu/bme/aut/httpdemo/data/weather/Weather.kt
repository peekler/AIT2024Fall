package hu.bme.aut.httpdemo.data.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("description")
    var description: String? = null,
    @SerialName("icon")
    var icon: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("main")
    var main: String? = null
)