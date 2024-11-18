package hu.bme.aut.httpdemo.data.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sys(
    @SerialName("country")
    var country: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("sunrise")
    var sunrise: Int? = null,
    @SerialName("sunset")
    var sunset: Int? = null,
    @SerialName("type")
    var type: Int? = null
)