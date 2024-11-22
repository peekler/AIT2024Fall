package hu.bme.aut.httpdemo.data.nasa


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CameraX(
    @SerialName("full_name")
    var fullName: String? = null,
    @SerialName("name")
    var name: String? = null
)