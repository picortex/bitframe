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
            println(
                """
                ${config.separator}
                Mock Email
                ${config.separator} 
                subject: ${draft.subject}
                from:    ${from.value}
                to:      ${to.joinToString(separator = ";") { it.value }}
                ${config.separator}
                ${draft.body}
                ${config.separator}
                """.trimIndent()
            )
        }
        draft.toMessage(from, to)
    }
}