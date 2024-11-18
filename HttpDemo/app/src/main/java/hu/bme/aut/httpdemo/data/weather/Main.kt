package hu.bme.aut.httpdemo.data.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("feels_like")
    var feelsLike: Double? = null,
    @SerialName("grnd_level")
    var grndLevel: Int? = null,
    @SerialName("humidity")
    var humidity: Int? = null,
    @SerialName("pressure")
    var pressure: Int? = null,
    @SerialName("sea_level")
    var seaLevel: Int? = null,
    @SerialName("temp")
    var temp: Double? = null,
    @SerialName("temp_max")
    var tempMax: Double? = null,
    @SerialName("temp_min")
    var tempMin: Double? = null
)