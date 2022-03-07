@file:JsExport

package pimonitor.core.businesses

import bitframe.core.Savable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class Business : Savable {
    abstract val name: String
    abstract val owningSpaceId: String
    abstract val email: String
    abstract val address: String
    abstract val logo: String?
}