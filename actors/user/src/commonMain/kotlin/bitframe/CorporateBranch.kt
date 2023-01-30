@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import geo.GeoLocation
import kollections.iListOf
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class CorporateBranch(
    val name: String,
    val contacts: List<Contact> = iListOf(),
    val location: GeoLocation? = null,
)