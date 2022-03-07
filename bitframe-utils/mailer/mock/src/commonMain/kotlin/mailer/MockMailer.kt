package mailer

import identifier.Email
import kotlinx.collections.interoperable.List
import kotlinx.coroutines.delay
import later.Later
import later.later

class MockMailer(val config: MockMailerConfig = MockMailerConfig()) : Mailer {
    override fun send(draft: EmailDraft, from: Email, to: List<Email>): Later<EmailMessage> = config.scope.later {
        delay(config.simulationTime)
        if (config.printToConsole) {
            val message = buildString {
                appendLine(config.separator)
                appendLine("Mock Email [Mock Mailer]")
                appendLine(config.separator)
                appendLine("Subject: ${draft.subject}")
                appendLine("From:    ${from.value}")
                appendLine("To:      ${to.joinToString(separator = ";") { it.value }}")
                appendLine(config.separator)
                appendLine(draft.body)
                appendLine(config.separator)
            }
            println(message)
        }
        draft.toMessage(from, to)
    }
}