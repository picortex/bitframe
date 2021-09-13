package pimonitor

import contacts.Email
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * The one who is being watched over a.k.a [Monitored] by the watcher a.k.a [Monitor]
 */
@JsExport
@Serializable
data class Monitored(
    val uid: String,
    val name: String,
    val email: Email,
    val logo: String? = null
)
