package mailer

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

    fun AddressInfo.toInternetAddress() = if (name == null) {
        InternetAddress(email.value)
    } else {
        InternetAddress(email.value, name)
    }

    override fun send(draft: EmailDraft, from: AddressInfo, to: List<AddressInfo>): Later<EmailMessage> = config.scope.later {
        val message = MimeMessage(session).apply {
            setFrom(from.toInternetAddress())
            addRecipients(Message.RecipientType.TO, to.map { it.toInternetAddress() }.toTypedArray())
            subject = draft.subject
            setText(draft.body)
        }
        Transport.send(message)
        draft.toMessage(from, to)
    }
}