package mailer

import identifier.Email
import kotlinx.collections.interoperable.List
import later.Later
import later.later
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SmtpMailer(val config: SmtpMailerConfig) : Mailer {

    val authenticator by lazy {
        object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(config.user, config.password)
            }
        }
    }

    val session by lazy { Session.getDefaultInstance(config.toProperties(), authenticator) }

    override fun send(draft: EmailDraft, from: Email, to: List<Email>): Later<EmailMessage> = config.scope.later {
        val message = MimeMessage(session).apply {
            setFrom(InternetAddress(from.value))
            addRecipients(Message.RecipientType.TO, to.map { InternetAddress(it.value) }.toTypedArray())
            subject = draft.subject
            setText(draft.body)
        }
        Transport.send(message)
        draft.toMessage(from, to)
    }
}