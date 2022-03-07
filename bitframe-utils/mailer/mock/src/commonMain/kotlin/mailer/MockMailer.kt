package mailer

import kotlinx.collections.interoperable.List
import kotlinx.coroutines.delay
import later.Later
import later.later

class MockMailer(val config: MockMailerConfig = MockMailerConfig()) : Mailer {
    fun AddressInfo.toDetailsString() = if (name == null) {
        email.value
    } else {
        "$name <${email.value}>"
    }

    override fun send(draft: EmailDraft, from: AddressInfo, to: List<AddressInfo>): Later<EmailMessage> = config.scope.later {
        delay(config.simulationTime)
        if (config.printToConsole) {
            val message = buildString {
                appendLine(config.separator)
                appendLine("Mock Email [Mock Mailer]")
                appendLine(config.separator)
                appendLine("Subject: ${draft.subject}")
                appendLine("From:    ${from.toDetailsString()}")
                appendLine("To:      ${to.joinToString(separator = ";") { it.toDetailsString() }}")
                appendLine(config.separator)
                appendLine(draft.body)
                appendLine(config.separator)
            }
            println(message)
        }
        draft.toMessage(from, to)
    }
}