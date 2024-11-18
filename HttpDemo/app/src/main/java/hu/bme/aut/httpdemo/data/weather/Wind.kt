package hu.bme.aut.httpdemo.data.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("deg")
    var deg: Int? = null,
    @SerialName("gust")
    var gust: Double? = null,
    @SerialName("speed")
    var speed: Double? = null
)