package pimonitor.core.businesses

import kotlinx.serialization.Serializable

@Serializable
data class BusinessFilter(
    val uid: String
)