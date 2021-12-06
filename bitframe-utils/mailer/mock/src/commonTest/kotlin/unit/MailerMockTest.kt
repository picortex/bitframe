package unit

import expect.expect
import identifier.Email
import kotlinx.coroutines.runTest
import later.await
import mailer.EmailDraft
import mailer.Mailer
import mailer.MockMailer
import mailer.MockMailerConfig
import kotlin.test.Ignore
import kotlin.test.Test

class MailerMockTest {
    val config = MockMailerConfig(
        simulationTime = 1000L,
        separator = "= = = = = = = = = ="
    )
    val mailer: Mailer = MockMailer(config)

    @Test
    @Ignore // Js has a weired RangeError bug  https://kotlinlang.slack.com/archives/C0B8L3U69/p1612432204190800
    fun should_easily_send_an_email() = runTest {
        val message = mailer.send(
            draft = EmailDraft(
                subject = "Test Draft",
                body = "This is a test email"
            ),
            from = Email("from@test.com"),
            to = Email("to@gmail.com"),
        ).await()
        expect(message).toBeNonNull()
    }
}