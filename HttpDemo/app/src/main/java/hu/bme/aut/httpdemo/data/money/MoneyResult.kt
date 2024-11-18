package hu.bme.aut.httpdemo.data.money


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoneyResult(
    @SerialName("base")
    var base: String? = null,
    @SerialName("date")
    var date: String? = null,
    @SerialName("provider")
    var provider: String? = null,
    @SerialName("rates")
    var rates: Rates? = null,
    @SerialName("terms")
    var terms: String? = null,
    @SerialName("time_last_updated")
    var timeLastUpdated: Int? = null,
    @SerialName("WARNING_UPGRADE_TO_V6")
    var wARNINGUPGRADETOV6: String? = null
)