@file:JsExport
package mailer

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class EmailMessage(
    val subject: String,
    val from: AddressInfo,
    val to: List<AddressInfo>,
    val body: String,
    val attachments: List<EmailAttachment> = listOf(),
    val status: List<EmailStatus>
)