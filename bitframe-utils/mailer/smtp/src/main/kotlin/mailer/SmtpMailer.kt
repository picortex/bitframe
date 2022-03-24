package mailer

import kotlinx.collections.interoperable.List
import later.Later
import later.later
import java.nio.file.Files
import java.nio.file.Paths
import javax.activation.DataHandler
import javax.activation.DataSource
import javax.activation.FileDataSource
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import javax.mail.util.ByteArrayDataSource

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
        val multipart = MimeMultipart("mixed");

        val message = MimeMessage(session).apply {
            setFrom(from.toInternetAddress())
            addRecipients(Message.RecipientType.TO, to.map { it.toInternetAddress() }.toTypedArray())
            subject = draft.subject
            var messageBodyPart = MimeBodyPart();
            messageBodyPart.setContent(draft.body, "text/html");
            multipart.addBodyPart(messageBodyPart);

            draft.attachments.forEachIndexed { index, attachment ->
                val attachmentBodyPart = MimeBodyPart()
                val fds = ByteArrayDataSource(attachment.content, attachment.type)

                attachmentBodyPart.dataHandler = DataHandler(fds)
                attachmentBodyPart.setHeader("Content-ID", "<attachment-$index>")
                attachmentBodyPart.setHeader("Content-Type", attachment.type)
                attachmentBodyPart.fileName = attachment.name

                multipart.addBodyPart(attachmentBodyPart)
            }
            
            setContent(multipart)
        }

        Transport.send(message)
        draft.toMessage(from, to)
    }
}