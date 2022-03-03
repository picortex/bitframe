package mailer

import identifier.Email
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import later.Later

/**
 * An interface to be used to send emails
 */
interface Mailer {
    fun send(
        draft: EmailDraft,
        from: Email,
        to: List<Email>,
    ): Later<EmailMessage>

    fun send(
        draft: EmailDraft,
        from: String,
        to: String
    ) = send(draft, Email(from), Email(to))

    fun send(
        draft: EmailDraft,
        from: Email,
        to: Email,
    ) = send(draft, from, listOf(to))
}