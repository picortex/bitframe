package pimonitor.core.businesses

import bitframe.core.Savable
import kotlin.js.JsExport

@JsExport
sealed interface Business : Savable {
    val spaceId: String
    val owningSpaceId: String
    val email: String
    val address: String
    val logo: String?
}