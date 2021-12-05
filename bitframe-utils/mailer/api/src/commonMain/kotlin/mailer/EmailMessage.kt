@file:JsExport
package mailer

import identifier.Email
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class EmailMessage(
    val subject: String,
    val from: Email,
    val to: List<Email>,
    val body: String,
    val attachments: List<EmailAttachment> = listOf(),
    val status: List<EmailStatus>
)