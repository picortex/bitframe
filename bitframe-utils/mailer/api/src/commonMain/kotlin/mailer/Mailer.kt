@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package mailer

import identifier.Email
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import later.Later
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * An interface to be used to send emails
 */
interface Mailer {

    @JsName("sendWithEmailListOf")
    fun send(
        draft: EmailDraft,
        from: Email,
        to: List<Email>,
    ): Later<EmailMessage>

    fun send(
        draft: EmailDraft,
        from: Email,
        to: Email,
    ) = send(draft, from, listOf(to))
}