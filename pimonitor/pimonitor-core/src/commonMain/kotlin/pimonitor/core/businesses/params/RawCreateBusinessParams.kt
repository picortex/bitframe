package pimonitor.core.businesses.params

import kotlin.js.JsExport

@JsExport
interface RawCreateBusinessParams {
    val businessName: String
    val contactName: String
    val contactEmail: String
}