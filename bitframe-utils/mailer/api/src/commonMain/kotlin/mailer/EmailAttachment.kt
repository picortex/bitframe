@file:JsExport

package mailer

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class EmailAttachment(
    val content: ByteArray,
    val type: String,
    val name: String
)