package hu.bme.aut.httpdemo.data.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeathResult(
    @SerialName("base")
    var base: String? = null,
    @SerialName("clouds")
    var clouds: Clouds? = null,
    @SerialName("cod")
    var cod: Int? = null,
    @SerialName("coord")
    var coord: Coord? = null,
    @SerialName("dt")
    var dt: Int? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("main")
    var main: Main? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("sys")
    var sys: Sys? = null,
    @SerialName("timezone")
    var timezone: Int? = null,
    @SerialName("visibility")
    var visibility: Int? = null,
    @SerialName("weather")
    var weather: List<Weather?>? = null,
    @SerialName("wind")
    var wind: Wind? = null
)