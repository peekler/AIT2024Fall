package hu.bme.aut.httpdemo.data.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    @SerialName("lat")
    var lat: Double? = null,
    @SerialName("lon")
    var lon: Double? = null
)