@file:JsExport

package mailer

import identifier.Email
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlin.js.JsExport

data class EmailDraft(
    val subject: String,
    val body: String,
    val attachments: List<EmailAttachment> = listOf()
) {
    fun toMessage(
        from: Email,
        to: List<Email>
    ) = EmailMessage(
        subject = subject,
        from = from,
        to = to,
        body = body,
        attachments = attachments,
        status = listOf()
    )
}